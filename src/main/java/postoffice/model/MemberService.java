package postoffice.model;

import java.util.Scanner;


import postoffice.view.MemberView;
import postoffice.view.PostView;
import postoffice.vo.MemberVO;

public class MemberService {
	
	static MemberDAO memberDao = new MemberDAO();
	PostService postService = new PostService();
	
	public String memberCorrect(MemberVO mem) {
		
		return null;
		
		
	}
	public String print(MemberVO mem) {
		
		return null;
		
	}
	public String memInsert(MemberVO mem) {
		// TODO Auto-generated method stub
		int result = memberDao.memberInsert(mem);
		
		return result>0? "등록":"이미 회원입니다.";
	}
	public String memLogin(String memId, String pass) {
		int result = memberDao.memLogin(memId, pass);
		return result>0? case4(memId): "로그인 실패!";
	}
	
	public static String memUpdate(MemberVO mem) {
		int result = memberDao.memUpdate(mem);
		return result>0? "수정성공":"수정실패"; 
	}
	
	private String deleteMember(String memId) {
		int result =memberDao.memberDelete(memId);
		return result>0? "탈퇴 성공 안녕하가세요.":"환영합니다.";
	}
	
	public String case4(String memId) {
	
		Scanner sc=new Scanner(System.in);
		System.out.println("로그인 성공! "+memId+"님 환영합니다!");
		while(true) {
		System.out.println("3.우편물 검색");	
		System.out.println("4.우편물 접수");
		
		System.out.println("5.마이페이지");
		System.out.println("6.로그아웃");
		String n = sc.next();
		if(n.equals("3")) {
			System.out.println("우편물 송장번호를 입력하세요");
			String postId = sc.next();
			PostView.print(postService.selectById(postId)); break;
		}
		if(n.equals("4")) {
		System.out.println("받는 사람을 입력하세요");
		String received = sc.next();
		System.out.println("주소를 입력하세요.");
		String address = sc.next();
		
		System.out.println("우편 종류를 선택하세요.");
		System.out.println("1.우편 2.소포");
		String str=sc.next();
		System.out.println("배송 종류를 선택하세요.");
		System.out.println("1.일반 2.퀵");
		String del=sc.next();
		PostView.print(postService.addPost(memId,received,address, str, del)); 
		
		}
		if(n.equals("5")) {
			System.out.println("1.개인정보 수정");
			System.out.println("2.접수한 수하물 리스트");
			System.out.println("3.회원탈퇴");
			String num=sc.next();
			if(num.equals("1")) {
				MemberVO mem = makeEmp(sc, memId);
				MemberView.print(MemberService.memUpdate(mem));
			}else if(num.equals("2")) {
				PostView.print(postService.selectByMemberId(memId));
			}else if(num.equals("3")){
				
				System.out.println("정말로 탈퇴하시겠습니까?");
				System.out.println("1.예 2.아니요");
				String choose=sc.next();
				if(choose.equals("1")) {
					deleteMember(memId);
				}	
				
			}else {
				System.out.println("잘못 눌렀습니다.");
			}
		}
		if(n.equals("6")) {
			//로그아웃 기능 수행
			break;
		}
		
	}
		return "작업 완료";
}

	private static MemberVO makeEmp(Scanner sc,String memId) {
		System.out.print("1.원하는 이름을 입력하세요.");
		String name = sc.next();
		//System.out.print("2.원하는 아이디를 입력하세요>>");
		//String id = sc.next();
		System.out.print("3.수정할 전화번호를 입력하세요>>");
		String contact = sc.next();
		System.out.print("4.수정할 주소를 입력하세요>>");
		String address = sc.next();
		System.out.print("5.수정할 이메일을 입력하세요>>");
		String email = sc.next();
		System.out.print("6.수정할 비밀번호를 입력하세요>>");
		String pass = sc.next();
		
		MemberVO mem = new MemberVO();
		mem.setName(name);
		mem.setContact(contact);
		mem.setAddress(address);
		mem.setEmail(email);
		mem.setMemberid(memId);
		mem.setPassword(pass);
		
		return mem;
	}
}

