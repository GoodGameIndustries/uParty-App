package com.GGI.uParty.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.GGI.uParty.Adapter;
import com.GGI.uParty.uParty;

public class HtmlLauncher extends GwtApplication implements Adapter {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(480, 320);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new uParty(this);
        }
}