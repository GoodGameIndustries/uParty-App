package com.GGI.uParty;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import com.GGI.uParty.uParty;

public class IOSLauncher extends IOSApplication.Delegate implements Adapter {
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        return new IOSApplication(new uParty(this), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }

	@Override
	public void showOrLoadInterstital() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUpdate() {
		// TODO Auto-generated method stub
		
	}
}