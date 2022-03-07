package mod.doyoubelieve.client.renderer;

import mod.doyoubelieve.client.model.RayGunModel;
import mod.doyoubelieve.common.item.RayGunItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class RayGunRenderer extends GeoItemRenderer<RayGunItem> {

    public RayGunRenderer() {
        super(new RayGunModel());
    }
}

