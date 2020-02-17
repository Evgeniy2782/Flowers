package com.example.evgen.flowersstories.Draweble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.evgen.flowersstories.MainActivity;
import com.example.evgen.flowersstories.MainActivity2;
import com.example.evgen.flowersstories.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class NavigationDrawer extends AppCompatActivity {

    private AccountHeader headerResult;
    private Drawer result;

    public void navigationDrawer(Toolbar toolbar) {

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withHeaderBackground(R.drawable.header)
                // .withSavedInstance(savedInstanceState)
                .build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withFullscreen(true)
                .withActionBarDrawerToggleAnimated(false)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName(R.string.drawer_item_home)
                                .withIcon(R.drawable.home)
                                .withIdentifier(1),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem()
                                .withName(R.string.drawer_item_skidki)
                                .withIcon(R.drawable.giftcard_black_24dp)
                                .withIdentifier(2),

                        new DividerDrawerItem(),
                        new SecondaryDrawerItem()
                                .withName(R.string.drawer_item_zakazy)
                                .withIcon(R.drawable.ic_local_library_black_24dp),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem()
                                .withName(R.string.drawer_item_dostavka)
                                .withIcon(R.drawable.ic_drive_eta_black_24dp),
                        new DividerDrawerItem(),

                        new SecondaryDrawerItem()
                                .withName(R.string.drawer_item_o_nas)
                                .withIcon(R.drawable.ic_report_black_24dp),
                        new DividerDrawerItem()
                )

                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

//                    Intent intent = new Intent(NavigationDrawer.this, Main2Activity.class);
                        //                      startActivity(intent);
                        //                    return false;

                        if (drawerItem != null)

                            if(drawerItem.getIdentifier() == 1) {

                                Intent homeIntent = new Intent(NavigationDrawer.this, MainActivity.class);
                                startActivity(homeIntent);

                            }if(drawerItem.getIdentifier()==2){

                            Intent intent = new Intent(NavigationDrawer.this, MainActivity2.class);
                            startActivity(intent);
                        }
                        return false;
                    }
                })
                .build();
    }
}
