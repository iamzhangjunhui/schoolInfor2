package com.cdxy.schoolinforapplication.ui.topic;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import com.cdxy.schoolinforapplication.R;
import com.cdxy.schoolinforapplication.adapter.topic.FolderAdapter;
import com.cdxy.schoolinforapplication.adapter.topic.SelectPhotoAdapter;
import com.cdxy.schoolinforapplication.model.topic.Folder;
import com.cdxy.schoolinforapplication.model.topic.SelectPhoto;
import com.cdxy.schoolinforapplication.ui.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SelectPhotoActivity extends BaseActivity implements View.OnClickListener {
    private SelectPhotoAdapter selectPhotoAdapter;
    private List<SelectPhoto> selectPhotos;//显示的图片
    private GridView mGridView;
    private static final int SELECT_ALL = 1;
    private List<Folder> folders;
    private FolderAdapter folderAdapter;
    private ListPopupWindow listPopupWindow;
    private TextView mTxtSelectFolder;
    private TextView mTxtOk;
    private ArrayList<SelectPhoto> selectPhotoList = new ArrayList<>();//用户选择的图片

    private LoaderManager.LoaderCallbacks loaderCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_photo);
        init();
        getSupportLoaderManager().initLoader(SELECT_ALL, null, loaderCallbacks);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SelectPhoto selectPhoto = (SelectPhoto) parent.getAdapter().getItem(position);
                if (selectPhoto.getIsSelect() == 0) {
                    selectPhoto.setIsSelect(1);
                } else {
                    selectPhoto.setIsSelect(0);
                }
                selectPhotoList.clear();
                for (int i = 0; i < selectPhotos.size(); i++) {
                    if (selectPhotos.get(i).getIsSelect() == 1) {
                        selectPhotoList.add(selectPhotos.get(i));
                    }
                }
                mTxtOk.setText("选择：" + selectPhotoList.size() + "/" + selectPhotos.size());
                selectPhotoAdapter.notifyDataSetChanged();


            }
        });
    }

    public void init() {
        selectPhotos = new ArrayList<>();
        selectPhotoAdapter = new SelectPhotoAdapter(SelectPhotoActivity.this, selectPhotos);
        mGridView = (GridView) findViewById(R.id.gridView_file_image);
        mGridView.setAdapter(selectPhotoAdapter);
        mTxtOk = (TextView) findViewById(R.id.ok);
        folders = new ArrayList<>();
        folderAdapter = new FolderAdapter(SelectPhotoActivity.this, folders);
        listPopupWindow = new ListPopupWindow(SelectPhotoActivity.this);
        listPopupWindow.setAdapter(folderAdapter);
        listPopupWindow.setWidth(300);
        listPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectPhotos.clear();
                selectPhotos.addAll(folders.get(position).getSelectPhotos());
                selectPhotoAdapter.notifyDataSetChanged();
                listPopupWindow.dismiss();
            }
        });
        mTxtSelectFolder = (TextView) findViewById(R.id.select_folder);
        listPopupWindow.setAnchorView(mTxtSelectFolder);
        loaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {

            private final String[] IMAGE_PROJECTION = {
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.DATE_ADDED,
                    MediaStore.Images.Media.MIME_TYPE,
                    MediaStore.Images.Media.SIZE,
                    MediaStore.Images.Media._ID};

            @Override
            public Loader onCreateLoader(int id, Bundle args) {
                if (id == SELECT_ALL) {
                    CursorLoader cursorLoader = new CursorLoader(SelectPhotoActivity.this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION, IMAGE_PROJECTION[4] + ">0 AND " + IMAGE_PROJECTION[3] + "=?" + " OR " + IMAGE_PROJECTION[3] + "=?", new String[]{"image/jpeg", "image/png"}, IMAGE_PROJECTION[2] + " DESC");
                    return cursorLoader;
                }
                return null;
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                if (data != null) {
                    if (data.getCount() > 0) {
                        //遍历得到的图片的路径并添加到显示图片GridView的数据源中。
                        while (data.moveToNext()) {
                            SelectPhoto selectPhoto = new SelectPhoto();
                            String path = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                            selectPhoto.setPath(path);
                            selectPhotos.add(selectPhoto);
                            //通过文件找到文件所在文件夹
                            File folderFile = new File(path).getParentFile();
                            //如果存在该文件夹
                            if (folderFile != null && folderFile.exists()) {
                                String folderPath = folderFile.getAbsolutePath();
                                //判断文件夹的相关信息是否存在本地保存文件夹信息的集合里面
                                Folder folder = getFolderFromPath(folderPath);
                                if (folder == null) {//不在，就将该文件夹信息添加到文件夹信息集合中
                                    Folder f = new Folder();
                                    f.setPath(folderPath);
                                    f.setName(folderFile.getName());
                                    List<SelectPhoto> folderPhotos = new ArrayList<>();
                                    folderPhotos.add(selectPhoto);
                                    f.setSelectPhotos(folderPhotos);
                                    folders.add(f);
                                } else {//存在，在对应的文件夹信息数据源的中添加图片
                                    folder.getSelectPhotos().add(new SelectPhoto(path, 0));
                                }
                            }

                        }
                        folderAdapter.notifyDataSetChanged();
                        selectPhotoAdapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onLoaderReset(Loader loader) {

            }
        };
    }

    //判断该文件夹的信息是否在本地数据源中保存
    private Folder getFolderFromPath(String path) {
        if (folders != null) {
            for (Folder folder : folders) {
                if (TextUtils.equals(folder.getPath(), path)) {
                    return folder;
                }
            }
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_folder:
                //选择图片文件夹
                listPopupWindow.show();
                break;
            case R.id.ok:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                ArrayList<String> objectList = new ArrayList<>();
                for (SelectPhoto selectPhoto : selectPhotoList) {
                    objectList.add(selectPhoto.getPath());
                }
                bundle.putStringArrayList("selectResult", objectList);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
