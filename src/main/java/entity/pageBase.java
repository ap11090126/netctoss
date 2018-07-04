package entity;

import java.util.List;

public class pageBase<M> {
    //��ǰҳ��,Ĭ��Ϊ��1ҳ
    private int currentPageNo =1;
    //��ҳ��
    private int totalPageCount;
    //�ܼ�¼��
    private int totalCount;
    //ҳ������
    private int pageSize=5;
    //��һҳ
    private int upPageNo;
    //��һҳ
    private int nextPageNo;
    
    //һҳ�Ľ��
    private List<M> list;
    
    
    public int getUpPageNo() {
        return upPageNo;
    }
    public void setUpPageNo(int upPageNo) {
        //�����ǰҳ>1
        if(this.currentPageNo>1){
            this.upPageNo = this.currentPageNo-1;
        }
        
    }
    public int getNextPageNo() {
        return nextPageNo;
    }
    public void setNextPageNo(int nextPageNo) {
        //�����ǰҳ>0��С����ҳ�������������һҳ
        if(this.currentPageNo>0 &&this.currentPageNo<this.totalPageCount){
            this.nextPageNo = currentPageNo+1;
        }
        
    }
    public List<M> getList() {
        return list;
    }
    public void setList(List<M> list) {
        this.list = list;
    }
    public int getCurrentPageNo() {
        return currentPageNo;
    }
    //�����ǰҳ�����0�����õ�ǰҳ��ֵ
    public void setCurrentPageNo(int currentPageNo) {
        if(currentPageNo>0){
            this.currentPageNo = currentPageNo;
        }
        
    }
    
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        if(totalCount>0){
            this.totalCount = totalCount;
        }
        
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        if(pageSize >0){
            this.pageSize = pageSize;
        }
        
    }
    
    public int getTotalPageCount() {
        return totalPageCount;
    }
    public void setTotalPageCount(int totalPageCount) {
        if(this.getTotalCount()%this.pageSize==0){
            this.totalPageCount = this.getTotalCount()/this.pageSize;
        }else if(this.getTotalCount()%this.pageSize>0){
            this.totalPageCount = this.getTotalCount()/this.pageSize +1 ;
        }else{
            this.totalPageCount =0;
        }
    }
    
    
    /*//������ҳ��
    public void setTotalPageCount(int ) {
        if(this.getTotalCount()%this.pageSize==0){
            this.totalPageCount = this.getTotalCount()/this.pageSize;
        }else if(this.getTotalCount()%this.pageSize>0){
            this.totalPageCount = this.getTotalCount()/this.pageSize +1 ;
        }else{
            this.totalPageCount =0;
        }
        
    }
    */
    
}