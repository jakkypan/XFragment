# XFragment
单Activity和多Fragment容器

具体功能包括：

    * 支持fragment到fragment之间的跳转
    * fragment的展示采用add/hide/show/pop的方式进行处理，并对hide/show有相应的回调
    * 支持fragment到actiivty的跳转
    * activity支持缺省fragment，并且也支持特地的fragemnt
    * 支持fragment跳转的launch mode，目前支持standard，singleTop和singleTask，并且模拟了类似activity的onNewIntent()
    * 支持类似activity的onActivityResult()的回调，fragment采用了监听回调
    * 支持跳转动画，以java bean的形式传递进去
    * fragment重叠问题：
        * 原因：系统在页面重启前，帮我们保存了Fragment的状态，但是在重启后恢复时，视图的可见状态没帮我们保存，而Fragment默认的是show状态。这样就导致了再次启动进入系统会从栈底向栈顶的顺序恢复Fragments，并且每一个恢复的Fragment都是以show()的方式显示出来，从而导致界面的重叠。
        * 方案：通过Fragment自身的saveInstanceState来保存状态，状态可以通过isHidden()获取
    * Activity State Loss问题修复框架：
       * 原因：Activity在onSaveInstanceState()方法被调用后，又执行了commit
       * 判断fragment是否在onResume状态之后，在的话先commit；否则先缓存起来，在onResume的事件触发时commit
