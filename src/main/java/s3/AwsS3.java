package s3;

import java.io.File;
import java.io.InputStream;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

//���� ���ε带 ���� s3 ��Ű��
public class AwsS3 {
	
	private SqlSessionTemplate sqlSession;
	
	//Amazon-s3-sdk
	private AmazonS3 s3Client;
	
	//���Ȼ��� ������ ���� ���
	private String accessKey = "#";	//IAM ���� ���� ������
	private String secretKey = "#";	//IAM���� ���� ��ũ�� ������
	private Regions clientRegion = Regions.AP_NORTHEAST_2;
	private String bucket = "#";	//��Ŷ ��

	
	//�⺻ ������
	public AwsS3() {
		
	}
	
	// asw S3 Client ����
	public AwsS3(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
		createS3Client();
	}
	
	//asw S3 Client ����
	private void createS3Client() {
		
		//AWS �����ϱ� ����
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		this.s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(clientRegion).build();
	}
	
	//file �ø���
	public void upload(File file, String key) {
		uploadToS3(new PutObjectRequest(this.bucket, key, file));
	}
	
	//multipartFile�� ����ؼ� �ø���
	public void upload(InputStream is, String key, String contentType, long contentLength) {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(contentType);
		metadata.setContentLength(contentLength);
		
		uploadToS3(new PutObjectRequest(this.bucket, key, is, metadata));
	}
	
	//������ ������ ���� ���ε� ���� �޼ҵ�鿡 �ʿ��� �޼ҵ�
	private void uploadToS3(PutObjectRequest request) {
		try {
			this.s3Client.putObject(request);
			System.out.println(String.format("[%s] upload complete", request.getKey()));
		}catch(AmazonServiceException e) {
			e.printStackTrace();
		}catch(SdkClientException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//����
	public void delete(String key) {
		try {
			//Delete ��ü ����
			DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(this.bucket, key);
			
			//Delete
			this.s3Client.deleteObject(deleteObjectRequest);
			System.out.println(String.format("[%s] deletion complete", key));
		}catch(AmazonServiceException e){
			e.printStackTrace();
		}catch(SdkClientException e) {
			e.printStackTrace();
		}
	}
}