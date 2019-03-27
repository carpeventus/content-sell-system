(function(w,d,u){
	var f = function(){};
	var util = {
		get:function(id){
			return d.getElementById(id);
		}
    };

	//layer
	var Layer = function(){
		this.init();
	};
	Layer.prototype = {
		init:function(){
			this.isConfirmed = false;
			this.template ='<div class="m-winwrapper">\
							    <div class="winwrapper">\
							        <div class="m-win m-win-simple">\
							            <div class="winhd"><h2 class="wintt">{title}</h2></div>\
							            <div class="winbd">{content}</div>\
							            <div class="winft">\
							                <button type="button" class="u-btn u-btn-primary u-btn-fw" data-action="confirm">{confirmText}</button>\
							                <button type="button" class="u-btn u-btn-normal u-btn-fw" data-action="cancel">取消</button>\
							            </div>\
							        </div>\
							    </div>\
							</div>';
			this.body = d.createElement('div');
			this.body.style.display = 'none';
			d.body.appendChild(this.body);
			this.body.addEventListener('click',function(e){
				var ele = e.target;
				var action = ele.dataset && ele.dataset.action;
				if(action == 'confirm'){
					this.confirm();
					return;
				}
				if(action == 'cancel'){
					this.hide();
					return;
				}
			}.bind(this),false);
		},
		reset:function(options){
			var options = options || {};
			this.title = options.title || '提示';
			this.content = options.content || '';
			this.confirmText = options.confirmText || '确定';
			this.onconfirm = options.onconfirm || f;
			this.isConfirmed = false;
			return this;
		},
		confirm:function(){
			if(!this.isConfirmed){
				this.onconfirm();
			}
			this.isConfirmed = true;
		},
		rend:function(){
			var html = this.template.replace('{title}',this.title).replace('{content}',this.content).replace('{confirmText}',this.confirmText);
			this.body.innerHTML = html;
		},
		show:function(){
			this.isConfirmed = false;
			this.rend();
			this.body.style.display = '';
		},
		hide:function(){
			this.isConfirmed = false;
			this.body.style.display = 'none';
		},
	};
	
	//loading
	var Loading = function(){
		this.init();
	};
	Loading.prototype = {
		init:function(){
			this.template ='<div class="v-load {class}"><div class="load"><i></i><b>{message}</b></div></div>';
			this.body = d.createElement('div');
			this.body.style.display = 'none';
			this.timer = null;
			d.body.appendChild(this.body);
		},
		rend:function(data){
			var html = data ? this.template.replace('{class}','v-load-result').replace('{message}',data.message) : this.template;
			this.body.innerHTML = html;
		},
		show:function(){
			this.rend();
			this.body.style.display = '';
		},
		result:function(message,callback){
			if(!message){
				return;
			}
			this.rend({message:message});
			if(this.timer){
				w.clearTimeout(this.timer);
			}
			this.timer = w.setTimeout(function(){
				this.hide();
				callback && callback();
			}.bind(this),1500);
		},
		hide:function(){
			this.body.style.display = 'none';
		}
	};

	w.util = util;
	w.Layer = Layer;
	w.Loading = Loading;
})(window,document);