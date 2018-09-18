package com.mob.network.interfaces;

public interface IRequest {

    void accept(IRequestProcessor processor, int connectionId);

}
