package kr.or.connect.reservation.service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dao.CommentDao;
import kr.or.connect.reservation.dao.FileDao;
import kr.or.connect.reservation.dto.File;
import kr.or.connect.reservation.dto.Comment;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentDao reservationUserCommentDao;
	private final FileDao fileInfoDao;
	private static final int LIMIT = 5;
	
	public int getTotalCount(int productId) {
		return reservationUserCommentDao.selectCountByProductId(productId);
	}
	
	public List<Comment.Info> getComments(int productId, int start) {
		List<Comment.Info> list = reservationUserCommentDao.selectCommentsByProductId(productId, start, LIMIT);
		
		for(Comment.Info comment : list){
			List<Comment.Image> imageList = reservationUserCommentDao.selectCommentImages(comment.getId());
			comment.setReservationUserCommentImages(imageList);
		}
		return list;
	}
		
	@Transactional(readOnly = false)
	public Comment.RegisterResponse registerComment(int userId, int productId, int reservationInfoId, int score, String comment, MultipartFile file){
		int reservationUserCommentId;
		try {
			Comment.DB reservationUserComment = addCommentDB(productId, reservationInfoId, userId, score, comment);
			reservationUserCommentId = reservationUserCommentDao.insertComment(reservationUserComment);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			throw e;
		}
		File.InfoDB fileInfo = addFileInfoDB(file);
		upload(file);
		int fileId = fileInfoDao.insertFileInfo(fileInfo);
		
		Comment.ImageDB reservationUserCommentImage = addCommentImage(reservationInfoId, reservationUserCommentId, fileId);
		reservationUserCommentDao.insertCommentImage(reservationUserCommentImage);
		
		Comment.RegisterResponse reservationUserCommentRegisterResponse = 
				makeReservationUserCommentRegisterResponse(productId);
		
		return reservationUserCommentRegisterResponse;
	}
	
	private Comment.RegisterResponse makeReservationUserCommentRegisterResponse(int productId) {
		return new Comment.RegisterResponse("success", productId);
	}

	private Comment.DB addCommentDB(int productId, int reservationInfoId, int userId, int score, String comment){
		return new Comment.DB(productId, reservationInfoId, userId, score, comment, LocalDateTime.now(), LocalDateTime.now());
	}
	
	private File.InfoDB addFileInfoDB(MultipartFile file){
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		return new File.InfoDB(file.getOriginalFilename(), "c:/tmp/" + file.getOriginalFilename(),
				"image/" + extension, 0, LocalDateTime.now(), LocalDateTime.now());
	}
	
	private Comment.ImageDB addCommentImage(int reservationInfoId, int reservationUserCommentId, int fileId) {
		return new Comment.ImageDB(reservationInfoId, reservationUserCommentId, fileId);
	}
	
	private void upload(MultipartFile file) {
		try (FileOutputStream fos = new FileOutputStream("c:/tmp/" + file.getOriginalFilename());
				InputStream is = file.getInputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
	}
}