package com.sy.s1.member;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.sy.s1.member.memberfile.MemberFileRepository;
import com.sy.s1.util.FileManager;
import com.sy.s1.util.FilePathGenerator;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private MemberFileRepository memberFileRepository;
	@Autowired
	private FilePathGenerator filePathGenerator;
	@Autowired
	private FileManager fileManager;
	
	@Value("${board.member.filePath}")
	private String filePath;

	public MemberVO memberJoin(MemberVO memberVO, MultipartFile files) throws Exception {
		File file =  filePathGenerator.getUseClassPathResource(filePath);
		
		String fileName = fileManager.saveFileCopy(files, file);
		
		MemberFileVO memberFileVO = new MemberFileVO();
		
		memberFileVO.setFileName(fileName);
		memberFileVO.setOriName(files.getOriginalFilename());
		
		memberVO.setMemberFileVO(memberFileVO);
		memberFileVO.setMemberVO(memberVO);
		
		memberVO = memberRepository.save(memberVO);
		
		 return memberVO;
	}

	public MemberVO memberLogin(MemberVO memberVO) throws Exception {
		memberVO = memberRepository.findByIdAndPw(memberVO.getId(), memberVO.getPw());
		
		return memberVO;
	}
	
	public void memberDelete(MemberVO memberVO)throws Exception {
		memberRepository.deleteById(memberVO.getId());
	}
	
	
	
	// 검증 메서드 추가 (return이 false면 에러가 없음, true면 에러가 있음)
	public boolean memberError(MemberVO memberVO, BindingResult bindingResult) throws Exception {
		
		boolean result = false; // 에러가 없음

		// 단일 if문으로 작성

		// 1. 기본 제공 검증
		if (bindingResult.hasErrors()) {
			result = true;
			
		} else {

			// 2. pw가 일치하는지 검증하기
			if (!memberVO.getPw().equals(memberVO.getPwCheck())) {
				bindingResult.rejectValue("pwCheck", "memberVO.password.notSame");// error가 있을때 form:errors의 path(변수이름),
																				// "message.properties에 적은 변수명"
				result = true;
			}

			// 3. id 중복검증 (DB조회)
			// memberVO.getId(): String 타입
			boolean check = memberRepository.existsById(memberVO.getId());
			System.out.println("왜");
			if (check) { //중복된 아이디 검사 (해당아이디가 있으면 true)
				bindingResult.rejectValue("id", "memberVO.id.already");
				result = true;
			}
		}
		return result;
	}

	
}
