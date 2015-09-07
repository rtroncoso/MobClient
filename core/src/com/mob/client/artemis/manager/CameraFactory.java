package com.mob.client.artemis.manager;

import com.artemis.EntityFactory;
import com.artemis.annotations.Bind;
import com.artemis.annotations.Sticky;
import com.artemis.annotations.UseSetter;
import com.mob.client.artemis.components.camera.Camera;
import com.mob.client.artemis.components.position.Pos;

/**
 * CameraFactory Class
 *
 * @author rt
 */
@Bind({Pos.class, Camera.class})
public interface CameraFactory extends EntityFactory<CameraFactory> {

    @Sticky CameraFactory camera();
    @Bind(Pos.class) CameraFactory position(float x, float y);

}
