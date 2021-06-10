package kr.or.connect.reservation.service;

import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.FileDao;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileInfoService {
	private final FileDao fileInfoDao;
	
	public String getFileName(int fileInfoId) {
		return fileInfoDao.selectFileName(fileInfoId);
	}
	
	public String getSaveFileName(int fileInfoId) {
		return fileInfoDao.selectSaveFileName(fileInfoId);
	}

	public String getContentType(int fileInfoId) {
		return fileInfoDao.selectContentType(fileInfoId);
	}
}