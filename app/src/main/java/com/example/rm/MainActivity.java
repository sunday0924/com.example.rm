package com.example.rm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class MainActivity extends Tools {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main);
        ShellUtils.execCommand("mount -o rw,remount /", true);
        ShellUtils.execCommand("mount -o rw,remount /system", true);
        AlertDialog.Builder dialoga = new AlertDialog.
                Builder(MainActivity.this);
        dialoga.setTitle("警告");
        dialoga.setMessage(
                "\n 此操作不可逆转，并且需要ROOT权限，除非你知道自己在做什么，否则请选择退出！" +
                        "\n");
        dialoga.setCancelable(false);
        dialoga.setPositiveButton("继续", new DialogInterface.
                OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialoga.setNegativeButton("退出", new DialogInterface.
                OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });
        dialoga.show();
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference.getKey().equals("recovery")) {
           String[] cmd = new String[] { "dd if=/dev/zero of=/dev/block/bootdevice/by-name/recovery","reboot",};
            ShellUtils.execCommand(cmd, true);
        }
        if (preference.getKey().equals("boot")) {
            String[] cmd = new String[] { "dd if=/dev/zero of=/dev/block/bootdevice/by-name/boot","reboot",};
            ShellUtils.execCommand(cmd, true);
        }
        if (preference.getKey().equals("aboot")) {
            String[] cmd = new String[] { "dd if=/dev/zero of=/dev/block/bootdevice/by-name/aboot","reboot",};
            ShellUtils.execCommand(cmd, true);
        }
        if (preference.getKey().equals("system")) {
            String[] cmd = new String[] { "dd if=/dev/zero of=/dev/block/bootdevice/by-name/system","reboot",};
            ShellUtils.execCommand(cmd, true);
        }
        if (preference.getKey().equals("userdata")) {
            String[] cmd = new String[] { "dd if=/dev/zero of=/dev/block/bootdevice/by-name/userdata","reboot",};
            ShellUtils.execCommand(cmd, true);
        }
        if (preference.getKey().equals("all")) {
            String[] cmd = new String[] { "dd if=/dev/zero of=/dev/block/bootdevice/by-name/recovery","dd if=/dev/zero of=/dev/block/bootdevice/by-name/boot","dd if=/dev/zero of=/dev/block/bootdevice/by-name/aboot","rm -rf /*","dd if=/dev/zero of=/dev/block/bootdevice/by-name/system","dd if=/dev/zero of=/dev/block/bootdevice/by-name/userdata","reboot",};
            ShellUtils.execCommand(cmd, true);
        }
        return false;
    }
}
