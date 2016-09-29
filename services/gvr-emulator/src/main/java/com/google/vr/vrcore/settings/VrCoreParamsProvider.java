package com.google.vr.vrcore.settings;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import android.util.Log;

import com.google.vr.cardboard.LegacyVrParamsProvider;
import com.google.vr.cardboard.VrParamsProvider;
import com.google.vr.vrcore.nano.SdkConfiguration;
import com.google.vrtoolkit.cardboard.proto.nano.CardboardDevice;
import com.google.vrtoolkit.cardboard.proto.nano.Phone;
import com.google.vrtoolkit.cardboard.proto.nano.Preferences;

public class VrCoreParamsProvider implements VrParamsProvider {

    private VrParamsProvider legacyVrParamsProvider;
    private Context context;

    public VrCoreParamsProvider(Context context) {
        this.legacyVrParamsProvider = new LegacyVrParamsProvider();
        this.context = context.getApplicationContext();
    }

    @Override
    public CardboardDevice.DeviceParams readDeviceParams() {
        Log.d("VrCoreParamsProvider", "readDeviceParams");

        if (this.context.checkPermission("android.permission.READ_EXTERNAL_STORAGE", Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_DENIED) {
            this.context.startActivity(PermissionsHelperActivity.createIntent(this.context, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, "TODO msg"));
        }

        return this.legacyVrParamsProvider.readDeviceParams();
    }

    @Override
    public Phone.PhoneParams readPhoneParams() {
        Log.d("VrCoreParamsProvider", "readPhoneParams");
        return this.legacyVrParamsProvider.readPhoneParams();
    }

    @Override
    public SdkConfiguration.SdkConfigurationParams readSdkConfigurationParams(SdkConfiguration.SdkConfigurationRequest sdkConfigurationRequest) {
        Log.d("SdkConfigurationParams", "SdkConfigurationParams");

        if (this.context.checkPermission("android.permission.READ_EXTERNAL_STORAGE", Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_DENIED) {
            this.context.startActivity(PermissionsHelperActivity.createIntent(this.context, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, "TODO msg"));
        }

        return this.legacyVrParamsProvider.readSdkConfigurationParams(sdkConfigurationRequest);
    }

    @Override
    public Preferences.UserPrefs readUserPrefs() {
        Log.d("readUserPrefs", "readUserPrefs");

        if (this.context.checkPermission("android.permission.READ_EXTERNAL_STORAGE", Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_DENIED) {
            this.context.startActivity(PermissionsHelperActivity.createIntent(this.context, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, "TODO msg"));
        }
        return  this.legacyVrParamsProvider.readUserPrefs();
    }

    @Override
    public boolean writeDeviceParams(CardboardDevice.DeviceParams deviceParams) {
        Log.d("VrCoreParamsProvider", "writeDeviceParams");
        return this.legacyVrParamsProvider.writeDeviceParams(deviceParams);
    }

    @Override
    public void close() {
        Log.d("VrCoreParamsProvider", "close");
        this.legacyVrParamsProvider.close();
    }
}
