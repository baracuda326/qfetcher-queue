package com.qfetcher.qfetcher.service.impl;

import com.qfetcher.qfetcher.model.RequestModel;
import com.qfetcher.qfetcher.repository.DataManifestRepository;
import com.qfetcher.qfetcher.service.DataListenerService;
import com.qfetcher.qfetcher.service.FillDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

import static com.qfetcher.qfetcher.utils.ParseDataUtils.createFileFromUrl;
import static com.qfetcher.qfetcher.utils.ParseDataUtils.getFileName;

@Service
public class DataListenerServiceImpl implements DataListenerService {
    private final DataManifestRepository dataManifestRepository;
    private FillDataService fillDataService;

    //***************************************************************************
    @Autowired
    public DataListenerServiceImpl(DataManifestRepository dataManifestRepository, FillDataService fillDataService) {
        this.dataManifestRepository = dataManifestRepository;
        this.fillDataService = fillDataService;
    }
    //***************************************************************************

    /**
     * @param request
     * @throws IOException
     */
    @Override
    public void fetchData(RequestModel request) throws IOException {
        String fileName = getFileName(request.getManifest());
        createFileFromUrl(request.getManifest(), fileName);
        Map<String, HashSet<String>> map = dataManifestRepository.getUrlsFromDat(request.getFilter(), fileName);
        fillDataService.fillData(map);
    }
}
