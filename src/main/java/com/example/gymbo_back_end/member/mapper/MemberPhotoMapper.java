package com.example.gymbo_back_end.member.mapper;

import com.example.gymbo_back_end.core.entity.MemberPhoto;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MemberPhotoMapper {

    public List<Map<String,Object>> toResponse(List<MemberPhoto> memberPhotos) throws IOException {

        List<Map<String,Object>> imageList = new ArrayList<>();

        for (MemberPhoto memberPhoto : memberPhotos) {
            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
            String path = memberPhoto.getFilePath();
            InputStream imageStream = new FileInputStream(absolutePath + path);
            byte[] imageByteArray = IOUtils.toByteArray(imageStream);
            imageStream.close();

            String base64EncodedImage = Base64.encodeBase64String(imageByteArray);

            Map<String, Object> imageInfo = new HashMap<>();
            imageInfo.put("fileName",memberPhoto.getOrigFileName());
            imageInfo.put("imageBytes",base64EncodedImage);
            imageList.add(imageInfo);
        }
        return imageList;
    }
}
