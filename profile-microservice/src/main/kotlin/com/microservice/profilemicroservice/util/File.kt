package com.microservice.profilemicroservice.util

import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class File {

    fun store(file: MultipartFile): ByteArray{
        return file.bytes;
    }

    fun download(file:MultipartFile): Resource {
        val fileBytes = file.bytes;
        val resource: Resource = ByteArrayResource(fileBytes)
        return  resource
    }
}