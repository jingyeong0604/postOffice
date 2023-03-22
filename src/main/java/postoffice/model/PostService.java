package postoffice.model;

import java.util.List;

import postoffice.vo.PostVO;

public class PostService {
	
	PostDAO postDao = new PostDAO();
	
	public PostVO selectById(String id) {
		return  postDao.selectById(id);
	}

	public String addPost(String id,String received, String address,String posttype, String del) {
		
		int result = postDao.addPost(id,received, address, posttype, del);
		return result>0?"접수 완료":"접수 실패";
	}

	public List<PostVO> selectByMemberId(String memId) {
//		int result=PostDAO.selectByMemberId(memId);
//		return result>0?result+"건 접수완료":"접수한 우편물이 없습니다.";
		return PostDAO.selectByMemberId(memId);	
		}
}
