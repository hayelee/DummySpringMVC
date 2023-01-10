package kr.or.ddit.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.dao.SampleDAO;

@Service
public class SampleService {
	private SampleDAO dao;

	@Inject // 버전이 바뀌더라도 injection 해줘요
	public SampleService(SampleDAO dao) { //어노테이션이 없어도 알아서 dao를 동작시킴
		super();
		this.dao = dao;
	}
	
	public String retrieveInfo() {
		return "info " + dao.selectData();
	}
}
