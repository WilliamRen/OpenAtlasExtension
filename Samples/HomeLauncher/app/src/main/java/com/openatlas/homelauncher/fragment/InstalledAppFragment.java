package com.openatlas.homelauncher.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.openatlas.framework.Atlas;
import com.openatlas.runtime.PackageLite;
import com.openatlas.homelauncher.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class InstalledAppFragment extends Fragment  implements AdapterView.OnItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    ListView mListView;
    // TODO: Rename and change types of parameters
    public static InstalledAppFragment newInstance(String param1, String param2) {
        InstalledAppFragment fragment = new InstalledAppFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public InstalledAppFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // TODO: Change Adapter to display your content
//        setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
//                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_apps_installed,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView= (ListView) view.findViewById(R.id.listView);
        List<org.osgi.framework.Bundle> mm=Atlas.getInstance().getBundles();
        ArrayList<String> list=new ArrayList<String>();
        for (org.osgi.framework.Bundle mObj:mm){


           System.err.println(mObj.toString());
            list.add( mObj.getLocation());
        }

        mListView.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, list));
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       String location= (String) parent.getAdapter().getItem(position);
       final PackageLite packageLite=   Atlas.getInstance().getBundlePackageLite(location);
        if (packageLite!=null){


            AlertDialog.Builder builder=new android.app.AlertDialog.Builder(getActivity());
            //设置对话框的图标
            builder.setIcon(R.mipmap.ic_launcher);
            //设置对话框的标题
            builder.setTitle("选择要启动的组件");
            //0: 默认第一个单选按钮被选中
            final String[] comp=new String[packageLite.components.size()];
                  packageLite.components.toArray(comp);
            builder.setSingleChoiceItems(comp, 0, new AlertDialog.OnClickListener(){
                public void onClick(DialogInterface dialog, int which) {
                    String className=(String)(comp[which]);
                    Intent mIntent=new Intent();
                    mIntent.setClassName(getActivity(),className);
                    startActivity(mIntent);
                }
            });

            //添加一个确定按钮
            builder.setPositiveButton(" 确 定 ", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            //创建一个单选按钮对话框
           builder.create().show();


        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
