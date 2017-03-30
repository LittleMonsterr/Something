package weiyu.com.vdhdemo;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by admin on 2017/3/30.
 *
 */

public class VDHRelativeLayout extends RelativeLayout {

	private ViewDragHelper viewDragHelper;
	private View dragView;
	private View leftdragView;
	private View autobackView;
	private int autobackViewOriginLeft;
	private int autodragViewOriginTop;

	public VDHRelativeLayout(Context context) {
		super(context);
		init();
	}

	public VDHRelativeLayout(Context context, AttributeSet attributeSet) {
		super(context);
		init();
	}

	private void init() {

		/*
		 * @params ViewGroup forParent 必须是一个ViewGroup
		 * @params float sensitivity 灵敏度
		 * @params Callback cb 回调
		 */
		viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
			/**
			 *
			 * 尝试捕获子view
			 * 这个方法用来返回可以被移动的View对象，我们可以通过判断child与我们想移动的View是的相等来控制谁能移动。
			 * @param child 尝试捕获的view
			 * @param pointerId
			 * @return 返回true表示允许捕获相关view,则当前view可以移动
			 */
			@Override
			public boolean tryCaptureView(View child, int pointerId) {
				return child== dragView || child== autobackView;
			}

			/**
			 * 计算child垂直方向的位置，top表示y轴坐标（相对于ViewGroup）
			 * 这个方法必须重写，要不然就不能移动了
			 * @param child 被纵向移动的子控件child
			 * @param top 被纵向移动的子控件child的顶边坐标top
			 * @param dy 被纵向移动的子控件child的移动距离dy
			 * @return 返回该child现在的位置
			 */
			@Override
			public int clampViewPositionVertical(View child, int top, int dy) {
				return top;
			}

			/**
			 * @param child 被横向移动的子控件child
			 * @param left 被横向移动的子控件child的左坐标left
			 * @param dx 被横向移动的子控件child的移动距离dx
			 * @return  返回该child现在的位置
			 */
			@Override
			public int clampViewPositionHorizontal(View child, int left, int dx) {
				if(left > getWidth() - child.getMeasuredWidth()){
					left = getWidth() - child.getMeasuredWidth();
				}else if(left < 0 ){
					left = 0;
				}
				return left;
			}

			/**
			 * 当前被捕获的view释放之后调用
			 * @param releasedChild
			 * @param xvel
			 * @param yvel
			 */
			@Override
			public void onViewReleased(View releasedChild, float xvel, float yvel) {
				super.onViewReleased(releasedChild, xvel, yvel);
				if(releasedChild == autobackView){
					/*
					使用dragHelper.settleCapturedViewAt方法设置autobackView的位置为它的初始位置。
					此方法内部是通过Scroller实现的，所以我们需要使用invalidate来刷新，同时需要重写computeScroll方法
					 */
					viewDragHelper.settleCapturedViewAt(autobackViewOriginLeft,autodragViewOriginTop);
					invalidate();
				}
			}

			@Override
			public void onEdgeDragStarted(int edgeFlags, int pointerId) {
				//主动去捕获view，可以不再在tryCaptureView方法中捕获
				viewDragHelper.captureChildView(leftdragView,pointerId);
			}

			/**
			 * 当view设置为clickable=true时，需要重写这个方法view才可以被拖拽
			 * @param child
			 * @return
			 */
			@Override
			public int getViewVerticalDragRange(View child) {
				return getMeasuredHeight() - child.getMeasuredHeight();
			}

			@Override
			public int getViewHorizontalDragRange(View child) {
				return getMeasuredWidth() - child.getMeasuredWidth();
			}
		});

		//设置左边缘可以被Drag
		viewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
	}

	/**
	 * 如果希望拖拽的子View是不可点击的，可以不重写onInterceptTouchEvent方法
	 * @param ev
	 * @return
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return viewDragHelper.shouldInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		viewDragHelper.processTouchEvent(event);
		return true;
	}

	@Override
	public void computeScroll() {
		//判断当前被捕获的子View是否还需要继续移动
		if(viewDragHelper.continueSettling(true)){
			invalidate();
		}
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		dragView = findViewById(R.id.dragView);
		leftdragView = findViewById(R.id.leftdragView);
		autobackView = findViewById(R.id.autobackView);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		autobackViewOriginLeft = autobackView.getLeft();
		autodragViewOriginTop = autobackView.getTop();
	}
}
