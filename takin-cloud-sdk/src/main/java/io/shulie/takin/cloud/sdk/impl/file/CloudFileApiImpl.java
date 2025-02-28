package io.shulie.takin.cloud.sdk.impl.file;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.TypeReference;

import org.springframework.stereotype.Service;
import io.shulie.takin.cloud.sdk.constant.EntrypointUrl;
import io.shulie.takin.cloud.entrypoint.file.CloudFileApi;
import io.shulie.takin.common.beans.response.ResponseResult;
import io.shulie.takin.cloud.sdk.service.CloudApiSenderService;
import io.shulie.takin.cloud.sdk.model.request.file.UploadRequest;
import io.shulie.takin.cloud.sdk.model.response.file.UploadResponse;
import io.shulie.takin.cloud.sdk.model.request.file.DeleteTempRequest;
import io.shulie.takin.cloud.sdk.model.request.filemanager.FileZipParamReq;
import io.shulie.takin.cloud.sdk.model.request.filemanager.FileCopyParamReq;
import io.shulie.takin.cloud.sdk.model.request.filemanager.FileDeleteParamReq;
import io.shulie.takin.cloud.sdk.model.request.filemanager.FileContentParamReq;
import io.shulie.takin.cloud.sdk.model.request.filemanager.FileCreateByStringParamReq;

/**
 * @author shiyajian
 * @author 张天赐
 * create: 2020-10-19
 */
@Service
public class CloudFileApiImpl implements CloudFileApi {

    @Resource
    CloudApiSenderService cloudApiSenderService;

    @Override
    public Map<String, Object> getFileContent(FileContentParamReq req) {
        return cloudApiSenderService.post(EntrypointUrl.join(EntrypointUrl.MODULE_FILE, EntrypointUrl.METHOD_FILE_CONTENT),
            req, new TypeReference<ResponseResult<Map<String, Object>>>() {}).getData();
    }

    @Override
    public Boolean deleteFile(FileDeleteParamReq req) {
        return cloudApiSenderService.post(EntrypointUrl.join(EntrypointUrl.MODULE_FILE, EntrypointUrl.METHOD_FILE_DELETE),
            req, new TypeReference<ResponseResult<Boolean>>() {}).getData();
    }

    @Override
    public void deleteTempFile(DeleteTempRequest req) {
        cloudApiSenderService.delete(EntrypointUrl.join(EntrypointUrl.MODULE_FILE, EntrypointUrl.METHOD_FILE_DELETE_TEMP),
            req, new TypeReference<ResponseResult<?>>() {});
    }

    @Override
    public Boolean copyFile(FileCopyParamReq req) {
        return cloudApiSenderService.post(EntrypointUrl.join(EntrypointUrl.MODULE_FILE, EntrypointUrl.METHOD_FILE_COPY),
            req, new TypeReference<ResponseResult<Boolean>>() {}).getData();

    }

    @Override
    public Boolean zipFile(FileZipParamReq req) {
        return cloudApiSenderService.post(EntrypointUrl.join(EntrypointUrl.MODULE_FILE, EntrypointUrl.METHOD_FILE_ZIP),
            req, new TypeReference<ResponseResult<Boolean>>() {}).getData();
    }

    @Override
    public String createFileByPathAndString(FileCreateByStringParamReq req) {
        return cloudApiSenderService.post(EntrypointUrl.join(EntrypointUrl.MODULE_FILE, EntrypointUrl.METHOD_FILE_CREATE_BY_STRING),
            req, new TypeReference<ResponseResult<String>>() {}).getData();
    }

    /**
     * 上传文件
     *
     * @param req 请求
     * @return 上传结果
     */
    @Override
    public List<UploadResponse> upload(UploadRequest req) {
        return cloudApiSenderService.uploadFile(EntrypointUrl.join(EntrypointUrl.MODULE_FILE, EntrypointUrl.METHOD_FILE_UPLOAD),
            req, "file", req.getFileList(), new TypeReference<ResponseResult<List<UploadResponse>>>() {}).getData();
    }

}
