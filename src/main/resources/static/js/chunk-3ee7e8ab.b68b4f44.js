(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3ee7e8ab"],{"0fd9":function(t,e,a){"use strict";a("99af"),a("4160"),a("caad"),a("13d5"),a("4ec9"),a("b64b"),a("d3b7"),a("ac1f"),a("2532"),a("3ca3"),a("5319"),a("159b"),a("ddb0");var n=a("ade3"),i=a("5530"),o=(a("4b85"),a("2b0e")),r=a("d9f7"),s=a("80d2"),c=["sm","md","lg","xl"],l=["start","end","center"];function d(t,e){return c.reduce((function(a,n){return a[t+Object(s["u"])(n)]=e(),a}),{})}var u=function(t){return[].concat(l,["baseline","stretch"]).includes(t)},m=d("align",(function(){return{type:String,default:null,validator:u}})),h=function(t){return[].concat(l,["space-between","space-around"]).includes(t)},f=d("justify",(function(){return{type:String,default:null,validator:h}})),v=function(t){return[].concat(l,["space-between","space-around","stretch"]).includes(t)},p=d("alignContent",(function(){return{type:String,default:null,validator:v}})),g={align:Object.keys(m),justify:Object.keys(f),alignContent:Object.keys(p)},b={align:"align",justify:"justify",alignContent:"align-content"};function w(t,e,a){var n=b[t];if(null!=a){if(e){var i=e.replace(t,"");n+="-".concat(i)}return n+="-".concat(a),n.toLowerCase()}}var C=new Map;e["a"]=o["a"].extend({name:"v-row",functional:!0,props:Object(i["a"])(Object(i["a"])(Object(i["a"])({tag:{type:String,default:"div"},dense:Boolean,noGutters:Boolean,align:{type:String,default:null,validator:u}},m),{},{justify:{type:String,default:null,validator:h}},f),{},{alignContent:{type:String,default:null,validator:v}},p),render:function(t,e){var a=e.props,i=e.data,o=e.children,s="";for(var c in a)s+=String(a[c]);var l=C.get(s);return l||function(){var t,e;for(e in l=[],g)g[e].forEach((function(t){var n=a[t],i=w(e,t,n);i&&l.push(i)}));l.push((t={"no-gutters":a.noGutters,"row--dense":a.dense},Object(n["a"])(t,"align-".concat(a.align),a.align),Object(n["a"])(t,"justify-".concat(a.justify),a.justify),Object(n["a"])(t,"align-content-".concat(a.alignContent),a.alignContent),t)),C.set(s,l)}(),t(a.tag,Object(r["a"])(i,{staticClass:"row",class:l}),o)}})},1681:function(t,e,a){},"5f28":function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("v-card",{staticClass:"mx-auto mt-2",staticStyle:{width:"60%",border:"none"},attrs:{outlined:"","min-height":"600"}},[a("v-card-title",[t._v(t._s(t.board.title))]),a("v-divider",{staticClass:"mx-4"}),a("v-card-subtitle",{staticClass:"text-right"},[a("span",[t._v("작성자: "+t._s(t.board.userName))]),a("span",{staticClass:"ml-5"},[t._v("작성일: "+t._s(t.board.modifyDate))])]),a("v-card-text",{staticStyle:{"min-height":"400px"}},[a("div",{staticClass:"text-left text--primary",domProps:{innerHTML:t._s(t.board.content)}})]),a("v-card-actions",[a("v-row",[a("v-col",{staticClass:"text-left",attrs:{cols:"8"}},[!0===t.userNameCheck?a("v-btn",{staticClass:"ma-2",attrs:{dark:""},on:{click:t.board_modify}},[t._v("수정")]):t._e(),!0===t.userNameCheck?a("v-btn",{staticClass:"ma-2",attrs:{outlined:""},on:{click:t.board_delete}},[t._v("삭제")]):t._e()],1),a("v-col",{staticClass:"text-right",attrs:{cols:"4"}},[a("v-btn",{staticClass:"ma-2",attrs:{dark:""},on:{click:t.goBack}},[t._v("뒤로가기")])],1)],1)],1),a("v-divider",{staticClass:"mx-4"}),a("CommentList")],1)],1)},i=[],o=(a("ac1f"),a("5319"),a("bc3a")),r=a.n(o),s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[t._l(t.commentList,(function(e,n){return a("div",{key:e.id},[a("Comment",{attrs:{commentObj:e,fetchCommentData:t.fetchCommentData}}),n!=t.commentList.length-1?a("v-divider"):t._e()],1)})),a("CommentWrite",{attrs:{fetchCommentData:t.fetchCommentData}})],2)},c=[],l=function(){var t=this,e=t.$createElement,a=t._self._c||e;return t.isAuthenticated?a("div",{staticClass:"ma-3",staticStyle:{height:"300px"}},[a("v-textarea",{attrs:{outlined:"",clearable:"","clear-icon":"mdi-close",placeholder:"댓글을 입력하세요.",rows:"4"},model:{value:t.content,callback:function(e){t.content=e},expression:"content"}}),a("div",{staticClass:"text-right"},[a("v-btn",{attrs:{outlined:""},on:{click:t.comment_write}},[t._v("댓글 등록")])],1)],1):t._e()},d=[],u=a("4360"),m={props:{fetchCommentData:Function},data:function(){return{content:""}},computed:{isAuthenticated:function(){return u["a"].getters.isAuthenticated}},methods:{comment_write:function(){var t=this;null!=this.$store.getters.getUserName&&""!=this.$store.getters.getUserName?this.content.length>0?r.a.post("/api/comment/write/"+this.$route.params.id,{userName:this.$store.getters.getUserName,content:this.content}).then((function(e){1!=e.data.result&&window.alert(e.data.msg),t.content="",t.fetchCommentData()})).catch((function(t){window.alert(t)})):window.alert("내용을 입력해주세요."):window.alert("회원만 댓글을 남길 수 있습니다.")}}},h=m,f=a("2877"),v=a("6544"),p=a.n(v),g=a("8336"),b=(a("a9e3"),a("5530")),w=(a("1681"),a("8654")),C=a("58df"),y=Object(C["a"])(w["a"]),_=y.extend({name:"v-textarea",props:{autoGrow:Boolean,noResize:Boolean,rowHeight:{type:[Number,String],default:24,validator:function(t){return!isNaN(parseFloat(t))}},rows:{type:[Number,String],default:5,validator:function(t){return!isNaN(parseInt(t,10))}}},computed:{classes:function(){return Object(b["a"])({"v-textarea":!0,"v-textarea--auto-grow":this.autoGrow,"v-textarea--no-resize":this.noResizeHandle},w["a"].options.computed.classes.call(this))},noResizeHandle:function(){return this.noResize||this.autoGrow}},watch:{lazyValue:function(){this.autoGrow&&this.$nextTick(this.calculateInputHeight)},rowHeight:function(){this.autoGrow&&this.$nextTick(this.calculateInputHeight)}},mounted:function(){var t=this;setTimeout((function(){t.autoGrow&&t.calculateInputHeight()}),0)},methods:{calculateInputHeight:function(){var t=this.$refs.input;if(t){t.style.height="0";var e=t.scrollHeight,a=parseInt(this.rows,10)*parseFloat(this.rowHeight);t.style.height=Math.max(a,e)+"px"}},genInput:function(){var t=w["a"].options.methods.genInput.call(this);return t.tag="textarea",delete t.data.attrs.type,t.data.attrs.rows=this.rows,t},onInput:function(t){w["a"].options.methods.onInput.call(this,t),this.autoGrow&&this.calculateInputHeight()},onKeyDown:function(t){this.isFocused&&13===t.keyCode&&t.stopPropagation(),this.$emit("keydown",t)}}}),x=Object(f["a"])(h,l,d,!1,null,"380e65b9",null),j=x.exports;p()(x,{VBtn:g["a"],VTextarea:_});var O=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("v-card",{staticClass:"ma-3",staticStyle:{border:"none"},attrs:{outlined:"","min-height":"20"}},[a("v-card-title",{staticClass:"text-left"},[a("span",{staticClass:"text-body-1 mr-2"},[t._v(t._s(t.commentObj.userName))]),a("span",{staticClass:"text-body-2 text--secondary mr-2"},[t._v("|")]),a("span",{staticClass:"text-body-2 text--secondary mr-2"},[t._v(t._s(t.commentObj.modifyDate))]),t.getUserName()===t.commentObj.userName?a("v-btn",{attrs:{fab:"",text:"","x-small":""},on:{click:function(e){return t.comment_modify(t.modifyCheck)}}},[a("v-icon",[t._v("mdi-pencil")])],1):t._e(),t.getUserName()===t.commentObj.userName?a("v-btn",{attrs:{fab:"",text:"","x-small":""},on:{click:function(e){return t.comment_delete(t.commentObj.id)}}},[a("v-icon",[t._v("mdi-delete")])],1):t._e()],1),a("v-card-text",{staticClass:"text-left"},[t.modifyCheck?a("span",[a("v-text-field",{attrs:{dense:"",outlined:""},model:{value:t.commentObj.content,callback:function(e){t.$set(t.commentObj,"content",e)},expression:"commentObj.content"}})],1):a("span",[t._v(t._s(t.commentObj.content))])])],1)],1)},k=[],$={props:{commentObj:Object,fetchCommentData:Function},data:function(){return{modifyCheck:!1}},methods:{getUserName:function(){return this.$store.getters.getUserName},comment_modify:function(t){var e=this;t&&(null!=this.$store.getters.getUserName&&""!=this.$store.getters.getUserName?this.commentObj.content.length>0?r.a.post("/api/comment/modify/"+this.$route.params.id,this.commentObj).then((function(t){1!=t.data.result&&window.alert(t.data.msg),e.fetchCommentData()})).catch((function(t){window.alert(t)})):window.alert("내용을 입력해주세요."):window.alert("작성자만 댓글을 수정할 수 있습니다.")),this.modifyCheck=!t},comment_delete:function(t){var e=this;window.confirm("댓글을 삭제하시겠습니까?")&&r.a.get("/api/comment/delete/"+this.$route.params.id+"/"+t).then((function(t){window.alert(t.data.msg),e.fetchCommentData()})).catch((function(t){window.alert(t),window.console.log(t)}))}}},N=$,D=(a("0481"),a("4069"),a("615b"),a("10d2")),V=a("297c"),S=a("1c87"),B=Object(C["a"])(V["a"],S["a"],D["a"]).extend({name:"v-card",props:{flat:Boolean,hover:Boolean,img:String,link:Boolean,loaderHeight:{type:[Number,String],default:4},raised:Boolean},computed:{classes:function(){return Object(b["a"])(Object(b["a"])({"v-card":!0},S["a"].options.computed.classes.call(this)),{},{"v-card--flat":this.flat,"v-card--hover":this.hover,"v-card--link":this.isClickable,"v-card--loading":this.loading,"v-card--disabled":this.disabled,"v-card--raised":this.raised},D["a"].options.computed.classes.call(this))},styles:function(){var t=Object(b["a"])({},D["a"].options.computed.styles.call(this));return this.img&&(t.background='url("'.concat(this.img,'") center center / cover no-repeat')),t}},methods:{genProgress:function(){var t=V["a"].options.methods.genProgress.call(this);return t?this.$createElement("div",{staticClass:"v-card__progress",key:"progress"},[t]):null}},render:function(t){var e=this.generateRouteLink(),a=e.tag,n=e.data;return n.style=this.styles,this.isClickable&&(n.attrs=n.attrs||{},n.attrs.tabindex=0),t(a,this.setBackgroundColor(this.color,n),[this.genProgress(),this.$slots.default])}}),H=a("80d2"),I=Object(H["e"])("v-card__actions"),L=Object(H["e"])("v-card__subtitle"),T=Object(H["e"])("v-card__text"),U=Object(H["e"])("v-card__title"),G=a("132d"),z=Object(f["a"])(N,O,k,!1,null,"42121e85",null),E=z.exports;p()(z,{VBtn:g["a"],VCard:B,VCardText:T,VCardTitle:U,VIcon:G["a"],VTextField:w["a"]});var F={data:function(){return{commentList:""}},components:{CommentWrite:j,Comment:E},created:function(){this.fetchCommentData()},methods:{fetchCommentData:function(){var t=this;r.a.get("/api/comment/list/"+this.$route.params.id).then((function(e){t.commentList=e.data.list,console.log(t.commentList)})).catch((function(t){window.alert(t)}))}}},R=F,P=a("ce7e"),A=Object(f["a"])(R,s,c,!1,null,"51ce5c4b",null),M=A.exports;p()(A,{VDivider:P["a"]});var J={components:{CommentList:M},data:function(){return{board:{id:"",title:"",content:"",userName:"",modifyDate:""}}},created:function(){this.fetchContentData()},computed:{userNameCheck:function(){return this.$store.getters.getUserName===this.board.userName}},methods:{fetchContentData:function(){var t=this;r.a.get("/api/board/content/"+this.$route.params.id).then((function(e){1===e.data.result?t.board=e.data.board:(window.alert(e.data.msg),t.$router.replace("/"))})).catch((function(t){window.alert(t)}))},board_modify:function(){this.$router.push("/modify/"+this.$route.params.id)},board_delete:function(){var t=this;window.confirm("게시글을 삭제하시겠습니까?")&&r.a.get("/api/board/delete/"+this.$route.params.id).then((function(e){window.alert(e.data.msg),1===e.data.result?t.$router.push("/"):t.$router.replace("/")})).catch((function(t){window.alert(t),window.console.log(t)}))},goBack:function(){this.$router.replace("/")}}},W=J,K=a("62ad"),q=a("0fd9"),Q=Object(f["a"])(W,n,i,!1,null,"74524280",null);e["default"]=Q.exports;p()(Q,{VBtn:g["a"],VCard:B,VCardActions:I,VCardSubtitle:L,VCardText:T,VCardTitle:U,VCol:K["a"],VDivider:P["a"],VRow:q["a"]})},"615b":function(t,e,a){},"8ce9":function(t,e,a){},ce7e:function(t,e,a){"use strict";var n=a("5530"),i=(a("8ce9"),a("7560"));e["a"]=i["a"].extend({name:"v-divider",props:{inset:Boolean,vertical:Boolean},render:function(t){var e;return this.$attrs.role&&"separator"!==this.$attrs.role||(e=this.vertical?"vertical":"horizontal"),t("hr",{class:Object(n["a"])({"v-divider":!0,"v-divider--inset":this.inset,"v-divider--vertical":this.vertical},this.themeClasses),attrs:Object(n["a"])({role:"separator","aria-orientation":e},this.$attrs),on:this.$listeners})}})}}]);
//# sourceMappingURL=chunk-3ee7e8ab.b68b4f44.js.map