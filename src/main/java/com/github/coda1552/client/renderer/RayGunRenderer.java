package com.github.coda1552.client.renderer;

import com.github.coda1552.client.model.RayGunModel;
import com.github.coda1552.common.item.RayGunItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class RayGunRenderer extends GeoItemRenderer<RayGunItem> {

    public RayGunRenderer() {
        super(new RayGunModel());
    }

}

