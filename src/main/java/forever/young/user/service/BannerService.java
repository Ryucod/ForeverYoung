package forever.young.user.service;

import java.util.List;

import forever.young.user.vo.BannerVO;
import forever.young.vo.ProductVO;

public interface BannerService {
	//Banner �ҷ�����
	List<BannerVO> getBannerList()throws Exception;
	//banner ��ǰ��������
    List<ProductVO> getCategoryProduct(ProductVO product) throws Exception;
}
