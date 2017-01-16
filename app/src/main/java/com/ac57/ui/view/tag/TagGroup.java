package com.ac57.ui.view.tag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.ac57.R;

import java.util.ArrayList;
import java.util.List;


public class TagGroup extends ViewGroup implements OnClickListener {

    /**
     * default horizontal space(dip) between tags
     */
    private static final int DEFAULT_HORIZONTAL_SPACE = 5;

    /**
     * default vertical space(dip) between tags
     */
    private static final int DEFAULT_VERTICAL_SPACE = 5;

    /**
     * default tag horizontal padding(dp)
     */
    private static final int DEFAULT_HORIZONTAL_PADDING = 8;

    /**
     * default tag vertical padding(dp)
     */
    private static final int DEFAULT_VERTICAL_PADDING = 2;

    /**
     * horizontal space(px) between tags
     */
    private int tagHorizontalSpace = 0;

    /**
     * vertical space(px) between tags
     */
    private int tagVerticalSpace = 0;

    /**
     * tagView horizontal padding
     */
    private int tagHorizontalPadding;

    /**
     * tagView vertical padding
     */
    private int tagVerticalPadding;

    /**
     * tagView text size
     */
    private int tagViewTextSize;

    /**
     * tagView text color
     */
    private int tagViewTextColor1;

    /**
     * tag stroke color
     */
    private int tagStrokeColor1;
    /**
     * tagView text color
     */
    private int tagViewTextColor2;

    /**
     * tag stroke color
     */
    private int tagStrokeColor2;

    /**
     * tag background color
     */
    private int tagBackgroundColor1;
    private int tagBackgroundColor2;

    private int tagCornerRadius;
    private List<Tag> list;
    private OnTagClickListener onTagClickListener;

    public TagGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initParam(context, attrs);
    }

    public TagGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagGroup(Context context) {
        this(context, null);
    }

    private void initParam(Context context, AttributeSet attrs) {
        tagHorizontalSpace = DisplayUtils.dip2px(DEFAULT_HORIZONTAL_SPACE, context);
        tagVerticalSpace = DisplayUtils.dip2px(DEFAULT_VERTICAL_SPACE, context);

        tagHorizontalPadding = DisplayUtils.dip2px(DEFAULT_HORIZONTAL_PADDING, context);
        tagVerticalPadding = DisplayUtils.dip2px(DEFAULT_VERTICAL_PADDING, context);
        tagViewTextSize = DisplayUtils.dip2px(14, context);
        tagViewTextColor1 = Color.BLACK;
        tagViewTextColor2 = Color.RED;
        tagBackgroundColor1 = Color.WHITE;
        tagBackgroundColor2 = Color.WHITE;
        tagStrokeColor1 = Color.BLACK;
        tagStrokeColor2 = Color.RED;
        tagCornerRadius = DisplayUtils.dip2px(13, context);

        if (attrs != null && !isInEditMode()) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TagGroup);
            Resources resources = context.getResources();

            int resID = ta.getResourceId(R.styleable.TagGroup_tagHorizontalSpace, -1);
            if (resID != -1) {
                tagHorizontalSpace = resources.getDimensionPixelSize(resID);
            } else {
                tagHorizontalSpace = ta.getDimensionPixelSize(R.styleable.TagGroup_tagHorizontalSpace, tagHorizontalSpace);
            }

            resID = ta.getResourceId(R.styleable.TagGroup_tagVerticalSpace, -1);
            if (resID != -1) {
                tagVerticalSpace = resources.getDimensionPixelSize(resID);
            } else {
                tagVerticalSpace = ta.getDimensionPixelSize(R.styleable.TagGroup_tagVerticalSpace, tagVerticalSpace);
            }

            resID = ta.getResourceId(R.styleable.TagGroup_tagHorizontalPadding, -1);
            if (resID != -1) {
                tagHorizontalPadding = resources.getDimensionPixelSize(resID);
            } else {
                tagHorizontalPadding = ta.getDimensionPixelSize(R.styleable.TagGroup_tagHorizontalPadding, tagHorizontalPadding);
            }

            resID = ta.getResourceId(R.styleable.TagGroup_tagVerticalPadding, -1);
            if (resID != -1) {
                tagVerticalPadding = resources.getDimensionPixelSize(resID);
            } else {
                tagVerticalPadding = ta.getDimensionPixelSize(R.styleable.TagGroup_tagVerticalPadding, tagVerticalPadding);
            }

            resID = ta.getResourceId(R.styleable.TagGroup_tagTextColor1, -1);
            if (resID != -1) {
                tagViewTextColor1 = resources.getColor(resID);
            } else {
                tagViewTextColor1 = ta.getColor(R.styleable.TagGroup_tagTextColor1, tagViewTextColor1);
            }


            resID = ta.getResourceId(R.styleable.TagGroup_tagTextColor2, -1);
            if (resID != -1) {
                tagViewTextColor2 = resources.getColor(resID);
            } else {
                tagViewTextColor2 = ta.getColor(R.styleable.TagGroup_tagTextColor1, tagViewTextColor2);
            }


            resID = ta.getResourceId(R.styleable.TagGroup_tagTextSize, -1);
            if (resID != -1) {
                tagViewTextSize = resources.getDimensionPixelSize(resID);
            } else {
                tagViewTextSize = ta.getDimensionPixelSize(R.styleable.TagGroup_tagTextSize, tagViewTextSize);
            }

            resID = ta.getResourceId(R.styleable.TagGroup_tagStrokeColor1, -1);
            if (resID != -1) {
                tagStrokeColor1 = resources.getColor(resID);
            } else {
                tagStrokeColor1 = ta.getColor(R.styleable.TagGroup_tagStrokeColor1, tagStrokeColor1);
            }

            resID = ta.getResourceId(R.styleable.TagGroup_tagStrokeColor2, -1);
            if (resID != -1) {
                tagStrokeColor2 = resources.getColor(resID);
            } else {
                tagStrokeColor2 = ta.getColor(R.styleable.TagGroup_tagStrokeColor1, tagStrokeColor2);
            }

            resID = ta.getResourceId(R.styleable.TagGroup_tagBackgroundColor1, -1);
            if (resID != -1) {
                tagBackgroundColor1 = resources.getColor(resID);
            } else {
                tagBackgroundColor1 = ta.getColor(R.styleable.TagGroup_tagBackgroundColor1, tagBackgroundColor1);
            }
            resID = ta.getResourceId(R.styleable.TagGroup_tagBackgroundColor2, -1);
            if (resID != -1) {
                tagBackgroundColor2 = resources.getColor(resID);
            } else {
                tagBackgroundColor2 = ta.getColor(R.styleable.TagGroup_tagBackgroundColor2, tagBackgroundColor2);
            }
            resID = ta.getResourceId(R.styleable.TagGroup_tagCornerRadius, -1);
            if (resID != -1) {
                tagCornerRadius = resources.getDimensionPixelSize(resID);
            } else {
                tagCornerRadius = ta.getDimensionPixelSize(R.styleable.TagGroup_tagCornerRadius, tagCornerRadius);
            }

            ta.recycle();
        }

        if (isInEditMode()) {
            List<Tag> tagList = new ArrayList<>();
            Tag tag;
            for (int i = 0; i < 7; i++) {
                tag = new Tag();
                tag.setType(Tag.TYPE_TEXT);
                tag.setTagText("tag" + i);
                tagList.add(tag);
            }

          /*  tag = new Tag();
            tag.setType(Tag.TYPE_ICON);
            tag.setIconID(R.drawable.icon_more);
            tagList.add(tag);*/
            this.list = tagList;
            setTags(tagList);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View child;
        // 布局总高度
        int layoutHeight = 0;
        // 布局总宽度
        int layoutWidth = MeasureSpec.getSize(widthMeasureSpec);
        // 当前x坐标
        int positionX = getPaddingLeft();
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            child.measure(widthMeasureSpec, heightMeasureSpec);

            if (i == 0 || (positionX + child.getMeasuredWidth()/* + tagHorizontalSpace*/ + getPaddingRight()) > layoutWidth) {
                layoutHeight += (child.getMeasuredHeight() + tagVerticalSpace);
                positionX = getLeft() + getPaddingLeft();
            } else {
                positionX += (child.getMeasuredWidth() + tagHorizontalSpace);
            }
        }
        layoutHeight -= tagVerticalSpace;
        // 设置控件高度
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), layoutHeight + getPaddingTop() + getPaddingBottom());
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int positionX = getPaddingLeft();
        int positionY = getPaddingTop();
        int widgetHeight = 0;
        View child;
        for (int i = 0; i < count; i++) {

            child = this.getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            if (height > widgetHeight) {
                widgetHeight = height;
            }

            if ((l + positionX /*+ tagHorizontalSpace*/ + width + getPaddingRight()) > r) {
                positionX = getPaddingLeft();
                positionY += (tagVerticalSpace + widgetHeight);
            }

            width = child.getMeasuredWidth();
            height = child.getMeasuredHeight();
            child.layout(positionX, positionY, positionX + width, positionY + height);
            positionX += (width + tagHorizontalSpace);
        }
    }

    public void setTags(List<Tag> tags) {
        this.list = tags;
        this.removeAllViews();
        if (tags != null) {
            for (int i = 0; i < tags.size(); i++) {
                Tag ta = tags.get(i);
                ta.setPo(i);
                TagView tagView = new TagView(getContext(), ta);
                tagView.setTextSize(tagViewTextSize);
                if (tags.get(i).getStat() == 0) {
                    tagView.setTextColor(tagViewTextColor1);
                } else {
                    tagView.setTextColor(tagViewTextColor2);
                }
                tagView.setHorizontalPadding(tagHorizontalPadding);
                tagView.setVerticalPadding(tagVerticalPadding);
                if (tags.get(i).getStat() == 0) {
                    tagView.setStrokeColor(tagStrokeColor1);
                } else {
                    tagView.setStrokeColor(tagStrokeColor2);
                }
                if (tags.get(i).getStat() == 0) {
                    tagView.setBackgroundColor(tagBackgroundColor1);
                } else {
                    tagView.setBackgroundColor(tagBackgroundColor2);
                }
                tagView.setTagCornerRadius(tagCornerRadius);
                tagView.initData();
                tagView.setOnClickListener(this);
                this.addView(tagView);
            }
        }
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.onTagClickListener = onTagClickListener;
    }

    public interface OnTagClickListener {

        void onTagClick(TagView tagView, Tag tag);
    }

    @Override
    public void onClick(View v) {
        if (onTagClickListener != null) {
            Tag vs = ((TagView) v).getTagData();
            vs.setStat(vs.getStat() == 0 ? 1 : 0);
            list.set(vs.getPo(), vs);
            onTagClickListener.onTagClick((TagView) v, vs);
            setTags(this.list);
        }
    }

}
