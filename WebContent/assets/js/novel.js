var handleNightModeAndFontSize = function() {
  window.scrollTo(0, 1);
  var sizeKey = 'fontSize';
  var modeKey = 'mode';
  var fontSize = 16;
  var mode = 'day';
  if ('localStorage' in window) {
      // 字体大小
      fontSize = localStorage.getItem(sizeKey);
      if (fontSize == null) {
          fontSize = 16 ;
          localStorage.setItem(sizeKey, fontSize);
      } else {
          fontSize = parseInt(fontSize);
      }
      // 黑夜白天模式
      mode = localStorage.getItem(modeKey);
      if (!mode || (mode !== 'day' && mode !== 'night')) {
          mode = 'day' ;
          localStorage.setItem(modeKey, mode);
      }
  }
  // 处理字体大小
  $('#content' ).css('font-size', fontSize + 'px');
  $('#font-minus' ).click(function() {
      if (fontSize <= 14){
    	  return;
      } else {
    	  fontSize -= 2;
      }      
      $('#content' ).css('font-size', fontSize + 'px');
      if ('localStorage' in window)
          localStorage.setItem(sizeKey, fontSize);
  });
  $('#font-plus' ).click(function() {
      if (fontSize >= 36){
    	  return;
      } else {
    	  fontSize += 2;
      }
      $('#content' ).css('font-size', fontSize + 'px');
      if ('localStorage' in window)
          localStorage.setItem(sizeKey, fontSize);
  });
  // 处理背景
  // 处理navbar
  var $navbar = $('.navbar');
  $navbar.removeClass('navbar-inverse navbar-default' );
  var c = (mode === 'day')? 'navbar-default' : 'navbar-inverse';
  $navbar.addClass(c);
  // 处理body
  $('body' ).removeClass('day night').addClass(mode);
  $('input[value="' + mode + '"]' ).prop('checked', true);
  $('.radio-inline' ).click(function(e) {
      if ( e.target.tagName !== 'INPUT')
          return;
      var mode = $(e.target ).val();
      // 处理body
      $('body' ).removeClass('day night').addClass(mode);
      if ('localStorage' in window)
          localStorage.setItem(modeKey, mode);
      // 处理navbar
      $navbar.removeClass('navbar-inverse navbar-default' );
      var c = (mode === 'day')? 'navbar-default' : 'navbar-inverse';
      $navbar.addClass(c);
  });
};

// 处理最近阅读进度、自动跳转
$(document).ready(function() {
    window.handleNightModeAndFontSize();
    var pathname = window.location.pathname;
    // 仅处理目录页面/阅读页面，阅读进度
    if ((pathname.startsWith("/chapters/") || pathname.startsWith("/chapter/")) && ('localStorage' in window)) {
        if (pathname.startsWith("/chapter/")) {
            var recentBooks = localStorage.getItem('recent-books');
            if (recentBooks) {
                recentBooks = JSON.parse(recentBooks);
            } else {
                recentBooks = [];
            }
            // 当前novelId
            var $a = $('.breadcrumb>li>a[novel-id]');
            var novelId = parseInt($a.attr('novel-id'));
            if (novelId) {
                var i = recentBooks.indexOf(novelId);
                if (i != -1) {
                    recentBooks.splice(i, 1);
                }
                recentBooks.unshift(novelId);
                if (recentBooks.length > 3) {
                    recentBooks.pop();
                }
                localStorage.setItem('recent-books', JSON.stringify(recentBooks));
                var info = {novel: $a.text(), pathname: pathname, chapter: $('.breadcrumb>li.active').text()};
                localStorage.setItem(novelId, JSON.stringify(info));
            }
        }

        // 滚动到最后的阅读位置
        var y = localStorage.getItem(pathname);
        if (y) {
            window.scrollTo(0, parseInt(y));
        }
        // 滚动的时候记录位置
        var lastY = 0;
        $(window).scroll(function() {
            y = $(window).scrollTop();
            if (Math.abs(y - lastY) > 100) {
                localStorage.setItem(pathname, y);
                lastY = y;
            }
        });
    }
});
