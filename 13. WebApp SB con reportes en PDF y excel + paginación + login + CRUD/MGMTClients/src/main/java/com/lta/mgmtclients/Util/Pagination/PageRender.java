package com.lta.mgmtclients.Util.Pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRender<T>{
    private String url;
    private Page<T> page;
    private int totalP;
    private int numItem;
    private int currentP;
    private List<PageItem> pages;

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.pages = new ArrayList<PageItem>();
        this.numItem = 5;   //elementos a mostrar en la paginacion de bootstrap, parte inferior.
        this.totalP = page.getTotalPages();
        this.currentP = page.getNumber() + 1;
        int since,until;
        if(this.totalP <= this.numItem){
            since = 1;
            until = this.totalP;
        }else{
            if(this.currentP <= this.numItem / 2){
                since = 1;
                until = this.numItem;
            }
            else if(this.currentP >= this.totalP - this.numItem /2){
                since = this.totalP - this.numItem +1;
                until = this.numItem;
            }
            else{
                since = this.currentP - this.numItem / 2;
                until = this.numItem;
            }
        }
        for (int i=0; i< until; i++){
            pages.add(new PageItem(since + i, this.currentP == until + i));
        }
    }

    public boolean isFirst(){
        return this.page.isFirst();
    }

    public boolean isLast(){
        return this.page.isLast();
    }

    public boolean isHasNext(){
        return this.page.hasNext();
    }

    public boolean isHasPreview(){
        return this.page.hasPrevious();
    }
}
