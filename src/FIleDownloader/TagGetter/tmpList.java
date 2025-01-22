package FIleDownloader.TagGetter;

import java.util.List;
import java.util.stream.Stream;

// List를 while문으로 쓰기 위한 클래스
public class tmpList {
        List list;
        int idx=-1;
        public tmpList(List list){
            this.list = list;
        }
        public int  getIdx(){
            return idx;
        }
        public String toString(){
            return Stream.of(list).toString();
        }
        public String getNext(){
            idx ++;
            if( list.size() > idx) {
                return (String) list.get(idx);
            }
            return null;
        }
        public boolean isNext(){
            if( list.size() > idx) {
                return true;
            }
            return  false;
        }
}
