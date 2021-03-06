package com.wzh.fruitgo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.wzh.fruitgo.Fragment.FarmFragment;
import com.wzh.fruitgo.Fragment.HomeFragmet;
import com.wzh.fruitgo.Fragment.StoreFragment;
import com.wzh.fruitgo.Fragment.TodoFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    private RadioGroup mRgGroup;
    private FragmentManager fragmentManager;
    private HomeFragmet homeFragment;
    private TodoFragment todoFragment;
    private FarmFragment farmFragment;
    private StoreFragment storeFragment;

    private static final String[] FRAGMENT_TAG = {"tab_home","tab_todo", "tab_farm","tab_store"};

    private final int show_tab_home = 0;//主页
    private final int show_tab_todo = 1;//番茄钟
    private final int show_tab_farm = 2;//农场
    private final int show_tab_store = 3;//商场
    private int defaultIndex = show_tab_home;//默认选中主页

    private int index = -100;// 记录当前的选项
    /**
     * 上一次界面 onSaveInstanceState 之前的tab被选中的状态 key 和 value
     */
    private static final String PRV_SELINDEX = "PREV_SELINDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //解决底部选项按钮被输入文字框顶上去
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);

        initActionBar();//初始化ActionBar
        initLeftMenuBtn();//初始化 设置侧拉界面中的按钮跳转
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(PRV_SELINDEX, defaultIndex);
        super.onSaveInstanceState(outState);
    }

//--------------------------------------------------------------------------------------------------
    /**
     * 初始化ActionBar
     */
    private void initActionBar() {
        //1.获取 actionbar 对象
        ActionBar actionBar = getSupportActionBar();
        //2.设置 图标、标题
        actionBar.setTitle("FruitGo");
        actionBar.setSubtitle("祝您健康每一天！");
        //3.启用、显示 home 按钮
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        //4.替换 home 按钮的图标 （现在显示的是 三条横线 符号）
        toggle = new ActionBarDrawerToggle(this,
                drawerLayout, 0, 0);
        //设置 actionbar 和 drawerlayout 同步状态
        toggle.syncState();
        //5.三条横线 添加动画 （现在显示的是 三条横线与←符号切换的效果）
        addAnamator(toggle);
    }

    private void addAnamator(ActionBarDrawerToggle toggle) {
        drawerLayout.setDrawerListener(toggle);
    }

    /**
     * 点击 actionbar 的 home 按钮，会执行该方法
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home://点击 actionbar 的 home 按钮的点击事件
                setHomeButtonState(item);
                //或
                //setHomeButtonState();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 设置 actionbar 的 home 按钮的点击事件
     *
     * @param item
     */
    private void setHomeButtonState(MenuItem item) {
        toggle.onOptionsItemSelected(item);
    }


//--------------------------------------------------------------------------------------------------
    /**
    * 侧拉框中多个界面的跳转
    */
    private void initLeftMenuBtn(){
        RelativeLayout userLayout = findViewById(R.id.user_layout);
        userLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo 编写界面跳换代码
                //代码示例
                //Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                //startActivity(intent);
            }
        });

        RelativeLayout orderLayout = findViewById(R.id.order_layout);
        orderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo 编写界面跳换代码
                //代码示例
                //Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                //startActivity(intent);
            }
        });

        RelativeLayout walletLayout = findViewById(R.id.wallet_layout);
        walletLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo 编写界面跳换代码
                //代码示例
                //Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                //startActivity(intent);
            }
        });

        RelativeLayout settingLayout = findViewById(R.id.setting_layout);
        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo 编写界面跳换代码
                //代码示例
                //Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                //startActivity(intent);
            }
        });
    }

//--------------------------------------------------------------------------------------------------
    /**
     * 初始化界面中显示的fragment
     */
    private void initView(){
        //获得RadioGroup控件
        mRgGroup = (RadioGroup)findViewById(R.id.rg_group);
        //选择设置Fragment
        setTabSelection(show_tab_home);
        //点击事件
        mRgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home://主页
                        setTabSelection(show_tab_home);
                        break;
                    case R.id.rb_todo://番茄钟
                        setTabSelection(show_tab_todo);
                        break;
                    case R.id.rb_farm://农场
                        setTabSelection(show_tab_farm);
                        break;
                    case R.id.rb_store://商城
                        setTabSelection(show_tab_store);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 根据传入的index参数来设置选中的tab页
     *
     * @param id 传入的选择的fragment
     */
    private void setTabSelection(int id) {
        //根据传入的index参数来设置选中的tab页。
        if (id == index) {
            return;
        }
        index = id;
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 设置切换动画
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case show_tab_home://主页的fragment
                mRgGroup.check(R.id.rb_home);
                if (homeFragment == null) {
                    homeFragment = new HomeFragmet();
                    transaction.add(R.id.fl_container, homeFragment, FRAGMENT_TAG[index]);
                } else {
                    transaction.show(homeFragment);
                }
                transaction.commit();
                break;
            case show_tab_todo://番茄钟的fragment
                mRgGroup.check(R.id.rb_todo);
                if (todoFragment == null) {
                    todoFragment = new TodoFragment();
                    transaction.add(R.id.fl_container, todoFragment, FRAGMENT_TAG[index]);
                } else {
                    transaction.show(todoFragment);
                }
                transaction.commit();
                break;
            case show_tab_farm://农场的fragment
                mRgGroup.check(R.id.rb_farm);
                if (farmFragment == null) {
                    farmFragment = new FarmFragment();
                    transaction.add(R.id.fl_container, farmFragment, FRAGMENT_TAG[index]);
                } else {
                    transaction.show(farmFragment);
                }
                transaction.commit();
                break;
            case show_tab_store://商城的fragment
                mRgGroup.check(R.id.rb_store);
                if (storeFragment == null) {
                    storeFragment = new StoreFragment();
                    transaction.add(R.id.fl_container, storeFragment, FRAGMENT_TAG[index]);
                } else {
                    transaction.show(storeFragment);
                }
                transaction.commit();
                break;
            default:
                break;
        }
    }

    /**
     * 隐藏fragment
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (todoFragment != null) {
            transaction.hide(todoFragment);
        }
        if (farmFragment != null) {
            transaction.hide(farmFragment);
        }
        if (storeFragment != null) {
            transaction.hide(storeFragment);
        }
    }

}