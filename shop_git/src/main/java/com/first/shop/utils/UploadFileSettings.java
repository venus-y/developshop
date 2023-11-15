package com.first.shop.utils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

import net.coobird.thumbnailator.Thumbnails;

// 업로드할 파일에 대한 설정을 도와주는 클래스
public class UploadFileSettings {
	
	// 썸네일 이미지 크기 설정
	static final int THUMB_WIDTH = 300;
	static final int THUMB_HEIGHT = 300;
	
	// 파일 업로드 메서드 매개변수로 업로드할 경로, 파일의 이름, 업로드될 파일의 내용과 날짜정보를 넘겨받는다.
	public static String fileUpload(String uploadPath,
			String fileName, byte[] fileData, String yyMMddPath) throws IOException {

		// 고유번호를 생성한다.
		UUID uuid = UUID.randomUUID();
		
		// 파일 이름과 uuid 사이에 '_' 구분자를 넣어 사용한다.
		String newFileName = uuid + "_" + fileName;
		
		System.out.println("파일이름 출력"+newFileName);
		
		// 이미지경로는 업로드경로에 날짜정보를 더해주는 것으로 설정
		String imgPath = uploadPath + yyMMddPath;
		
		//업로드될 파일에 대한 정보를 세팅한다, 경로와 파일이름을 지정해준다.
		File targetFile = new File(imgPath,newFileName);
		
		// 파일데이터를 대상으로 지정된 파일경로에 복사해준다.
		FileCopyUtils.copy(fileData, targetFile);
		
		// 썸네일 파일 이미지는 파일명 앞에 "s_" 구분자를 붙인다.
		String thumbFileName = "s_" + newFileName;  
		
		// 업로드경로 + 파일구분자 + 파일이름을 합쳐 이미지파일경로를 생성한다.
		File image = new File(imgPath + File.separator + newFileName);
		
		System.out.println("타겟"+ targetFile.getPath());
		System.out.println("이미지"+ image.getPath());
		
		
		// 썸네일 파일의 이름은  업로드경로와 파일구분자 + "s_" + 파일 구분자 파일이름 형태로 생성한다.
		File thumbImage = new File(imgPath + File.separator + "s" + File.separator + thumbFileName);
	
		// 이미지파일경로 존재하는 것이 확인되면 계속 진행한다.
		if(image.exists()) {
			// 썸네일을 저장할 부모 디렉토리가 생성돼있지 않다면 해당 디렉토리를 생성한다.
			// 부모 디렉토리란 ? 파일 구분자를 기분으로 하나 앞에 있는 경로 
			// 예시: ex\ex2에선 ex가 ex2의 부모경로가 된다.
			thumbImage.getParentFile().mkdirs();
			//  원본이미지를 대상으로 초기에 상수로 설정했던 너비,높이 값을 적용하고 썸네일 파일을 만든다.
			Thumbnails.of(image).size(THUMB_WIDTH, THUMB_HEIGHT).toFile(thumbImage);
		}
		// 파일명을 반환환다.
		return newFileName;
	}
	
	
		// 경로를 계산해주는 메서드, 파일을 업로드할 경로를 매개변수로 넘겨준다.
		public static String calcPath(String uploadPath) {
		// 캘린더 클래스 객체를 생성
			Calendar calendar = Calendar.getInstance();
			
			// 파일구분자 뒤에 추출해온 년도값을 더해준 것으로 경로생성
			String yearPath = File.separator + calendar.get(Calendar.YEAR);
			
			// 앞에 작성한 년도경로 뒤에 덧붙여서 경로를 생성해준다. "00" <- 입력한 숫자가 1일 경우 01 과 같이 두자리로 만들어준다.
			// Calendar의 month는 0부터 시작하기 때문에 경로 생성시 month 값에 + 1 을 더해주어야 실제로 사용하는 값과 같아진다.
			String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH)+1);
			
			// 일자 역시 마찬가지로 앞서 작성한 년도,월 경로와 같이 작성한다.
			String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE));
			
			//경로를 생성한다.
			makeDir(uploadPath, yearPath, monthPath, datePath);
			// 썸네일 경로
			makeDir(uploadPath, yearPath, monthPath, datePath + "\\s");
		
			// 일자를 반환
			return datePath;
		}
		
		// 저장경로를 생성해주는 메서드, 업로드경로명과 paths에 가변인자로 넘겨준다. 차례대로 년도,월,일자가 paths배열에 담기게 된다. 
		private static void makeDir(String uploadPath, String...  paths) {
			
			// path 배열의 마지막인 일자의 경로가 이미 존재할 경우 경로를 생성하지 않고 return
			if(new File(paths[paths.length - 1]).exists()){
				return;
			}
			
			// 업로드 경로 + 년도,월,일자순으로 경로를 하나씩 생성한다.
			for(String path : paths) {
				File dir = new File(uploadPath + path);
			
				// 경로가 존재하지 않을 경우 생성한다. -> 두 번 검사하는 이유는 첫번째 if문에선 일자까지 존재하는지 확인했다.
				// 년도와 월 경로는 이미 존재할 수 있기 때문에 if로 한번 더 거른다.
				if(!dir.exists()) {
					dir.mkdir();
				}
			}
		}
		
}
