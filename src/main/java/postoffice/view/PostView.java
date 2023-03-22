package postoffice.view;

import java.util.List;

import postoffice.vo.PostVO;

public class PostView {

	public static void print(PostVO postVO) {
		System.out.println("============우편물 정보============");
		if(postVO==null) {
			System.out.println("해당 우편물은 존재하지 않습니다.");
		}else 
		System.out.println(postVO.toString());
		
	}
	
	public static void print(List<PostVO> postlist) {
		System.out.println("============접수한 우편물 정보============");
		for(PostVO post:postlist) {
			System.out.println(post);
		}
	}
	
	public static void print(String message) {
		System.out.println(message);
	}
	
}
