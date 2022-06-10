package net.xdclass.xdvideo.service;

import net.xdclass.xdvideo.model.bo.UserBo;
import org.elasticsearch.action.index.IndexResponse;

import java.io.IOException;

public interface UserEsService {
    IndexResponse save(UserBo userBo) throws IOException;

    Object delete(String id) throws IOException;

    Object get(String id) throws IOException;

    Object update(UserBo userBo) throws IOException;
}