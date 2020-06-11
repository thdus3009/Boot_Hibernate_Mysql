package com.sy.s1.board.notice;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sy.s1.board.BoardService;
import com.sy.s1.member.MemberFileVO;
import com.sy.s1.util.FileManager;
import com.sy.s1.util.FilePathGenerator;
import com.sy.s1.util.Pager;

@Service
@Transactional(rollbackFor= Exception.class) //이 메서드들이 exception 발생하면 rollback 하라는 뜻
public class NoticeService implements BoardService{

	@Autowired
	private NoticeRepository noticeRepository;
	
	@Value("${board.notice.filePath}")
	private String filePath;	
	
	@Autowired
	private FilePathGenerator pathGenerator;

	@Autowired
	private FileManager fileManager;

	
	public NoticeVO setInsert(NoticeVO noticeVO, MultipartFile [] files) throws Exception {
		
		File file = pathGenerator.getUserResouceLoader(filePath);
		List<NoticeFileVO> ar = new ArrayList<NoticeFileVO>();
		NoticeFileVO noticeFileVO = new NoticeFileVO();
		
		for(MultipartFile multipartFile:files) {
			if(multipartFile.getSize()<=0) {
				continue;
			}
			String fileName = fileManager.saveFileCopy(multipartFile, file);
			
			

			noticeFileVO.setFileName(fileName);
			System.out.println("fileName : " + fileName);
			noticeFileVO.setOriName(multipartFile.getOriginalFilename());
			System.out.println("oriName : " +multipartFile.getOriginalFilename());
			
			ar.add(noticeFileVO);
			//noticeFileVO = noticeRepository.save(noticeFileVO);

		}
		
		System.out.println("arsize : " + ar.size());
		noticeVO.setNoticeFileVOs(ar);
		noticeFileVO.setNoticeVO(noticeVO);
		noticeVO = noticeRepository.save(noticeVO);		
		return noticeVO;
	}
	
	public List<NoticeVO> getselectList(Pager pager) throws Exception{
		//Pageable pageable, String search
		
		List<NoticeVO> ar = new ArrayList<NoticeVO>();
		
		pager.makeRow(); //몇번페이지인지
		pager.makePage(noticeRepository.countByTitleContaining(pager.getSearch()));
																//	, 한페이지당 몇개씩 가져올건지 , 정렬방식
		Pageable pageable = PageRequest.of((int)pager.getStartRow(), pager.getPerPage(), Sort.Direction.DESC,"num"); //정렬 : Sort.Direction.DESC,"num"
		
		
		if(pager.getKind().equals("writer")) {
			
			 ar = noticeRepository.findByTitleContaining(pager.getSearch(), pageable);
			 pager.makePage(noticeRepository.countByWriterContaining(pager.getSearch()));
			 
			 
		}else if(pager.getKind().equals("contents")) {
			
			ar = noticeRepository.findByTitleContaining(pager.getSearch(), pageable);
			pager.makePage(noticeRepository.countByContentsContaining(pager.getSearch()));
			
		}else {
			
			ar = noticeRepository.findByTitleContaining(pager.getSearch(), pageable);
			pager.makePage(noticeRepository.countByTitleContaining(pager.getSearch()));
		}
		
		return ar;
		//return noticeRepository.findByTitleContaining(pager.getSearch(), pageable);
	}
	
	public NoticeVO getSelectOne(NoticeVO noticeVO) throws Exception {
		Optional<NoticeVO> opt = noticeRepository.findById(noticeVO.getNum());
		noticeVO = opt.get();
		
		return noticeVO;
	}
	
}
