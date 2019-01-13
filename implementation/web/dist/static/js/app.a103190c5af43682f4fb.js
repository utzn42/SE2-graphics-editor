webpackJsonp([1],{0:function(e,t){},"164i":function(e,t){},D9yW:function(e,t){},DNSE:function(e,t){},FY0i:function(e,t){},H9m8:function(e,t){},Lssf:function(e,t){},NHnr:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=n("7+uW"),s=n("8+8L"),i={name:"Canvas",data:function(){return{canvas:{}}},methods:{updateCanvas:function(e){this.canvas=e}},created:function(){var e=this;de.$on("response",function(t){return e.updateCanvas(t.body.canvas)})}},o={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"canvas"},[t("div",{staticClass:"svg",domProps:{innerHTML:this._s(this.canvas.html)}})])},staticRenderFns:[]};var l=n("VU/8")(i,o,!1,function(e){n("Lssf")},"data-v-93aff0ac",null).exports,r={name:"Connection",props:{baseUrl:String},data:function(){return{projectID:""}},methods:{createProject:function(){var e=this;console.log('GET REQUEST TO URL="'+this.baseUrl+'/create":'),this.$http.get(this.baseUrl+"/create").then(function(t){console.log("DATA RECEIVED:"),console.log(t),t.body.hasOwnProperty("canvas")&&(e.projectID=t.body.projectID,de.$emit("response",t))})},loadProject:function(e){var t=this;console.log('GET REQUEST TO URL="'+this.baseUrl+"/"+e+'":'),this.$http.get(this.baseUrl+"/"+e).then(function(e){console.log("DATA RECEIVED:"),console.log(e),e.body.hasOwnProperty("canvas")&&(t.projectID=e.body.projectID,de.$emit("response",e))})},postRequest:function(e,t){var n=this;console.log('POST REQUEST TO URL="'+this.baseUrl+"/"+this.projectID+"/"+e+'":'),console.log("SENDING DATA:"),console.log(t),this.$http.post(this.baseUrl+"/"+this.projectID+"/"+e,t).then(function(e){console.log("DATA RECEIVED:"),console.log(e),e.body.hasOwnProperty("canvas")&&(n.projectID=e.body.projectID,de.$emit("response",e))})},downloadRequest:function(e){console.log('OPENING URL="/'+this.projectID+"/download/"+e+'":'),window.open(this.baseUrl+"/"+this.projectID+"/download/"+e)}},created:function(){var e=this;de.$on("createProject",function(){return e.createProject()}),de.$on("loadProject",function(t){return e.loadProject(t)}),de.$on("editProject",function(t){return e.postRequest("editProject",t)}),de.$on("addShape",function(t){return e.postRequest("addShape",t)}),de.$on("addLayerGroup",function(t){return e.postRequest("addLayerGroup",t)}),de.$on("modifyShape",function(t){return e.postRequest("modifyShape",t)}),de.$on("editElement",function(t){return e.postRequest("editElement",t)}),de.$on("transformElement",function(t){return e.postRequest("transformElement",t)}),de.$on("removeElement",function(t){return e.postRequest("removeElement",t)}),de.$on("download",function(t){return e.downloadRequest(t)}),this.createProject()}},c={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"connection"},[t("h1",[this._v("Project "+this._s(this.projectID))])])},staticRenderFns:[]};var u=n("VU/8")(r,c,!1,function(e){n("sCLo")},"data-v-503d28fa",null).exports,d={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"modal-wrapper"},[t("div",{staticClass:"modal-content"},[this._t("default")],2)])},staticRenderFns:[]};var h=n("VU/8")({name:"ModalComponent"},d,!1,function(e){n("YS5L")},"data-v-7f37a05e",null).exports,p={name:"DownloadMenu",data:function(){return{fileType:"SVG"}},methods:{download:function(){de.$emit("download",this.fileType),this.close()},close:function(){this.$emit("close")}}},v={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"download-menu"},[n("label",[e._v("Please select the desired image format: ")]),e._v(" "),n("select",{directives:[{name:"model",rawName:"v-model",value:e.fileType,expression:"fileType"}],on:{change:function(t){var n=Array.prototype.filter.call(t.target.options,function(e){return e.selected}).map(function(e){return"_value"in e?e._value:e.value});e.fileType=t.target.multiple?n:n[0]}}},[n("option",{attrs:{value:"SVG"}},[e._v("SVG")]),e._v(" "),n("option",{attrs:{value:"PNG"}},[e._v("PNG")]),e._v(" "),n("option",{attrs:{value:"JPG"}},[e._v("JPG")])]),e._v(" "),n("br"),e._v(" "),n("button",{on:{click:function(t){e.download()}}},[e._v("Download image")]),e._v(" "),n("button",{on:{click:function(t){e.close()}}},[e._v("Cancel")])])},staticRenderFns:[]};var m={name:"BottomBarMenu",components:{DownloadMenu:n("VU/8")(p,v,!1,function(e){n("zShX")},"data-v-4cdac3be",null).exports,ModalComponent:h},data:function(){return{downloadMenuVisible:!1}},methods:{showDownloadMenu:function(e){this.downloadMenuVisible=e}}},f={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"bar-menu"},[n("button",{on:{click:function(t){e.showDownloadMenu(!0)}}},[e._v("Download")]),e._v(" "),n("modal-component",{directives:[{name:"show",rawName:"v-show",value:e.downloadMenuVisible,expression:"downloadMenuVisible"}]},[n("download-menu",{on:{close:function(t){e.showDownloadMenu(!1)}}})],1)],1)},staticRenderFns:[]};var _=n("VU/8")(m,f,!1,function(e){n("tGao")},"data-v-ee80d092",null).exports,y={render:function(){var e=this.$createElement;return(this._self._c||e)("div")},staticRenderFns:[]};var b=n("VU/8")({name:"TopBarMenu"},y,!1,function(e){n("oFqF")},"data-v-07f12449",null).exports,E=n("Zx67"),k=n.n(E),C=n("zwoO"),w=n.n(C),M=n("Pf15"),g=n.n(M),S=n("Zrlr"),x=n.n(S),$=function e(t,n){x()(this,e),this.id=t,this.visible=n},T=function(e){function t(e,n,a){x()(this,t);var s=w()(this,(t.__proto__||k()(t)).call(this,e,n));return s.shape=a,s}return g()(t,e),t}($),P=function(e){function t(e,n,a){x()(this,t);var s=w()(this,(t.__proto__||k()(t)).call(this,e,n));return s.elements=a,s}return g()(t,e),t}($),R={name:"Layer",data:function(){return{isSelected:!1}},methods:{checkIDAndSend:function(e){e===this.id&&this.pushThis()},pushThis:function(){this.isSelected=!0,de.$emit("pushElement",new T(this.id,this.visible,this.shape))},unselectIfNotThis:function(e){e.id!==this.id&&(this.isSelected=!1)}},props:{id:Number,visible:Boolean,shape:{}},created:function(){var e=this;de.$on("requestElement",function(t){return e.checkIDAndSend(t)}),de.$on("pushElement",function(t){return e.unselectIfNotThis(t)})}},A={render:function(){var e=this,t=e.$createElement;return(e._self._c||t)("li",{staticClass:"clickable",class:{selected:e.isSelected},on:{click:function(t){t.stopPropagation(),e.pushThis()}}},[e._v("\n  "+e._s(e.shape.shapeType)+"\n")])},staticRenderFns:[]};var D=n("VU/8")(R,A,!1,function(e){n("DNSE")},"data-v-caee1e18",null).exports,I={name:"AddShapeMenu",props:{modal:Boolean,elementId:Number,addBefore:Boolean,addInto:Boolean},data:function(){return{shapesRootPackage:"shapes"}},methods:{addShape:function(e){this.addInto?de.$emit("addShape",{shapeType:e,placeIntoElementID:this.elementId}):this.addBefore?de.$emit("addShape",{shapeType:e,placeBeforeElementID:this.elementID}):de.$emit("addShape",{shapeType:e}),this.modal&&this.close()},addLayerGroup:function(){this.addInto?de.$emit("addLayerGroup",{placeIntoElementID:this.elementId}):this.addBefore?de.$emit("addLayerGroup",{placeBeforeElementID:this.elementId}):de.$emit("addLayerGroup",{}),this.modal&&this.close()},close:function(){this.$emit("close")}}},L={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"add-shape-menu"},[n("ul",{staticClass:"shape-selection",class:{"modal-list":e.modal}},[n("li",{staticClass:"clickable",on:{click:function(t){e.addShape("LINE")}}},[e._v("\n      Line\n    ")]),e._v(" "),n("li",{staticClass:"clickable",on:{click:function(t){e.addShape("CIRCLE")}}},[e._v("\n      Circle\n    ")]),e._v(" "),n("li",{staticClass:"clickable",on:{click:function(t){e.addShape("POLYGON")}}},[e._v("\n      Polygon\n    ")]),e._v(" "),n("li",{staticClass:"clickable",on:{click:function(t){e.addShape("REGULAR_POLYGON")}}},[e._v("\n      Regular Polygon\n    ")]),e._v(" "),n("li",{staticClass:"clickable",on:{click:function(t){e.addShape("ELLIPSE")}}},[e._v("\n      Ellipse\n    ")]),e._v(" "),n("li",{staticClass:"clickable",on:{click:function(t){e.addShape("STAR")}}},[e._v("\n      Star\n    ")]),e._v(" "),n("li",{staticClass:"clickable",on:{click:function(t){e.addShape("TEXT")}}},[e._v("\n      Text\n    ")])]),e._v(" "),n("hr"),e._v(" "),n("ul",{class:{"modal-list":e.modal}},[n("li",{staticClass:"clickable",on:{click:function(t){e.addLayerGroup()}}},[e._v("\n      Layer Group\n    ")])]),e._v(" "),n("hr"),e._v(" "),e.modal?n("ul",{staticClass:"modal-list"},[n("li",{staticClass:"cancel clickable",on:{click:function(t){e.close()}}},[e._v("\n      Cancel\n    ")])]):e._e()])},staticRenderFns:[]};var N=n("VU/8")(I,L,!1,function(e){n("oX4Z")},"data-v-741da297",null).exports,U={name:"LayerGroup",components:{AddShapeMenu:N,ModalComponent:h,Layer:D},data:function(){return{isSelected:!1,showAddShape:!1}},methods:{checkIDAndSend:function(e){e===this.id&&this.pushThis()},pushThis:function(){this.isSelected=!0,de.$emit("pushElement",new P(this.id,this.visible,this.elements))},unselectIfNotThis:function(e){e.id!==this.id&&(this.isSelected=!1)}},props:{id:Number,visible:Boolean,elements:Array},created:function(){var e=this;de.$on("requestElement",function(t){return e.checkIDAndSend(t)}),de.$on("pushElement",function(t){return e.unselectIfNotThis(t)})}},V={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("li",{staticClass:"clickable",class:{selected:e.isSelected,"selected-dark":!e.isSelected},on:{click:function(t){t.stopPropagation(),e.pushThis()}}},[e._v("\n  Layer Group\n  "),n("ul",{staticClass:"white-border",staticStyle:{"margin-top":"20px","margin-left":"5px"}},[e._l(e.elements,function(e){return[e.hasOwnProperty("shape")?[n("layer",{attrs:{id:e.id,visible:e.visible,shape:e.shape}})]:[n("layer-group",{attrs:{id:e.id,visible:e.visible,elements:e.elements}})]]}),e._v(" "),0!==e.elements.length?n("hr"):e._e(),e._v(" "),n("li",{on:{click:function(t){t.stopPropagation(),e.showAddShape=!0}}},[n("i",[e._v("Add Element")])])],2),e._v(" "),n("ModalComponent",{directives:[{name:"show",rawName:"v-show",value:e.showAddShape,expression:"showAddShape"}]},[n("AddShapeMenu",{attrs:{modal:"","element-id":e.id,"add-into":""},on:{close:function(t){e.showAddShape=!1}}})],1)],1)},staticRenderFns:[]};var G={name:"LayerMenuNew",components:{AddShapeMenu:N,ModalComponent:h,LayerGroup:n("VU/8")(U,V,!1,function(e){n("fPxB")},"data-v-6ee9ed52",null).exports,Layer:D},data:function(){return{elements:[],showAddShape:!1}},methods:{updateElements:function(e){this.elements=e.elements,console.log(this.elements)}},created:function(){var e=this;de.$on("response",function(t){return e.updateElements(t.body.canvas)})}},O={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"layer-menu-grid"},[n("ul",[e._l(e.elements,function(e){return[e.hasOwnProperty("shape")?[n("layer",{attrs:{id:e.id,visible:e.visible,shape:e.shape}})]:[n("layer-group",{attrs:{id:e.id,visible:e.visible,elements:e.elements}})]]}),e._v(" "),0!==e.elements.length?n("hr"):e._e(),e._v(" "),n("li",{staticClass:"clickable",on:{click:function(t){t.stopPropagation(),e.showAddShape=!0}}},[n("i",[e._v("Add Element")])]),e._v(" "),n("ModalComponent",{directives:[{name:"show",rawName:"v-show",value:e.showAddShape,expression:"showAddShape"}]},[n("AddShapeMenu",{attrs:{modal:""},on:{close:function(t){e.showAddShape=!1}}})],1)],2)])},staticRenderFns:[]};var F={name:"LeftMenu",components:{LayerMenuNew:n("VU/8")(G,O,!1,function(e){n("O+wd")},"data-v-3f446fe2",null).exports}},j={render:function(){var e=this.$createElement;return(this._self._c||e)("layer-menu-new")},staticRenderFns:[]};var B=n("VU/8")(F,j,!1,function(e){n("FY0i")},"data-v-2215cf3f",null).exports,q=function e(t,n,a,s){x()(this,e),this.fillColour={label:"Fill Colour",type:"colour",value:t},this.strokeColour={label:"Stroke Colour",type:"colour",value:n},this.strokeWidth={label:"Stroke Width",type:"simple",value:a},this.opacity={label:"Opacity",type:"range:0-1",value:s}},W=function(e){function t(e,n,a,s,i,o){x()(this,t);var l=w()(this,(t.__proto__||k()(t)).call(this,a,s,i,o));return l.center={label:"Center",type:"coordinate",value:e},l.radius={label:"Radius",type:"simple",value:n},l}return g()(t,e),t}(q),Y=function(e){function t(e,n,a,s,i,o,l){x()(this,t);var r=w()(this,(t.__proto__||k()(t)).call(this,n,a,s,i,o,l));return r.edgeAmount={label:"Number of Edges",type:"simple",value:e},r}return g()(t,e),t}(W),X=function(e){function t(e,n,a,s,i,o,l,r){x()(this,t);var c=w()(this,(t.__proto__||k()(t)).call(this,n,a,s,i,o,l,r));return c.radius.label="Outer Radius",c.edgeAmount.label="Number of Outer Corners",c.innerRadius={label:"Inner Radius",type:"simple",value:e},c}return g()(t,e),t}(Y),z=function(e){function t(e,n,a,s,i,o,l){x()(this,t);var r=w()(this,(t.__proto__||k()(t)).call(this,s,i,o,l));return r.center={label:"Center",type:"coordinate",value:e},r.radiusX={label:"Radius X",type:"simple",value:n},r.radiusY={label:"Radius Y",type:"simple",value:a},r}return g()(t,e),t}(q),H=function(e){function t(e,n,a,s,i){x()(this,t);var o=w()(this,(t.__proto__||k()(t)).call(this,n,a,s,i));return o.coordinates={label:"Points",type:"coordinateArray",value:e},o}return g()(t,e),t}(q),Z=function(e){function t(e,n,a,s,i){return x()(this,t),w()(this,(t.__proto__||k()(t)).call(this,e,n,a,s,i))}return g()(t,e),t}(H),J=function(e){function t(e,n,a,s,i,o,l,r){x()(this,t);var c=w()(this,(t.__proto__||k()(t)).call(this,i,o,l,r));return c.center={label:"Center",type:"coordinate",value:e},c.displayText={label:"Text",type:"simple",value:n},c.font={label:"Font",type:"simple",value:a},c.fontSize={label:"Font Size",type:"simple",value:s},c}return g()(t,e),t}(q),Q={name:"ShapeEditMenuNew",data:function(){return{shapeType:"",shapeData:null,shapeModel:{},selectedElement:{}}},methods:{updateEdit:function(e){if(this.selectedElement=e,this.selectedElement.hasOwnProperty("shape")){var t=this.selectedElement.shape;switch(this.shapeModel=t,this.shapeType=t.shapeType,this.shapeType){case"CIRCLE":this.shapeData=new W(t.center,t.radius,t.fillColour,t.strokeColour,t.strokeWidth,t.opacity);break;case"REGULAR_POLYGON":this.shapeData=new Y(t.edgeAmount,t.center,t.radius,t.fillColour,t.strokeColour,t.strokeWidth,t.opacity);break;case"STAR":this.shapeData=new X(t.innerRadius,t.edgeAmount,t.center,t.radius,t.fillColour,t.strokeColour,t.strokeWidth,t.opacity);break;case"ELLIPSE":this.shapeData=new z(t.center,t.radiusX,t.radiusY,t.fillColour,t.strokeColour,t.strokeWidth,t.opacity);break;case"LINE":this.shapeData=new H(t.coordinates,t.fillColour,t.strokeColour,t.strokeWidth,t.opacity);break;case"POLYGON":this.shapeData=new Z(t.coordinates,t.fillColour,t.strokeColour,t.strokeWidth,t.opacity);break;case"TEXT":this.shapeData=new J(t.center,t.displayText,t.font,t.fontSize,t.fillColour,t.strokeColour,t.strokeWidth,t.opacity);break;default:console.log("Unexpected Type")}}else this.shapeData=null},addVertex:function(e){e.push({x:0,y:0}),this.edit()},removeVertex:function(e,t){e.splice(t,1),this.edit()},edit:function(){this.shapeModel.elementID=this.selectedElement.id,de.$emit("modifyShape",this.shapeModel),console.log(this.shapeModel)}},created:function(){var e=this;de.$on("pushElement",function(t){return e.updateEdit(t)})}},K={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return null!==e.shapeData&&void 0!==e.shapeData?n("div",{staticClass:"shape-edit-form"},[n("ul",[n("li",{staticClass:"selected-dark"},[e._v("Shape Attributes")]),e._v(" "),e._l(e.shapeData,function(t,a){return[n("li",[n("label",{staticClass:"header-label"},[e._v(e._s(t.label))]),n("br"),e._v(" "),"simple"===t.type?[n("input",{directives:[{name:"model",rawName:"v-model",value:e.shapeModel[a],expression:"shapeModel[key]"}],attrs:{type:"text"},domProps:{value:e.shapeModel[a]},on:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key,"Enter"))return null;e.edit()},input:function(t){t.target.composing||e.$set(e.shapeModel,a,t.target.value)}}})]:e._e(),e._v(" "),"range:0-1"===t.type?[n("input",{directives:[{name:"model",rawName:"v-model",value:e.shapeModel[a],expression:"shapeModel[key]"}],attrs:{type:"range",min:"0",max:"1",step:"any"},domProps:{value:e.shapeModel[a]},on:{change:function(t){e.edit()},__r:function(t){e.$set(e.shapeModel,a,t.target.value)}}})]:e._e(),e._v(" "),"colour"===t.type?[n("input",{directives:[{name:"model",rawName:"v-model",value:e.shapeModel[a].rgbColour,expression:"shapeModel[key].rgbColour"}],attrs:{type:"color"},domProps:{value:e.shapeModel[a].rgbColour},on:{change:function(t){e.edit()},input:function(t){t.target.composing||e.$set(e.shapeModel[a],"rgbColour",t.target.value)}}}),n("br"),e._v(" "),n("label",[e._v("Opacity ")]),e._v(" "),n("input",{directives:[{name:"model",rawName:"v-model",value:e.shapeModel[a].opacity,expression:"shapeModel[key].opacity"}],attrs:{type:"range",min:"0",max:"1",step:"any"},domProps:{value:e.shapeModel[a].opacity},on:{change:function(t){e.edit()},__r:function(t){e.$set(e.shapeModel[a],"opacity",t.target.value)}}})]:e._e(),e._v(" "),"coordinate"===t.type?[n("label",[e._v("x: ")]),e._v(" "),n("input",{directives:[{name:"model",rawName:"v-model",value:e.shapeModel[a].x,expression:"shapeModel[key].x"}],attrs:{type:"text"},domProps:{value:e.shapeModel[a].x},on:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key,"Enter"))return null;e.edit()},input:function(t){t.target.composing||e.$set(e.shapeModel[a],"x",t.target.value)}}}),n("br"),e._v(" "),n("label",[e._v("y: ")]),e._v(" "),n("input",{directives:[{name:"model",rawName:"v-model",value:e.shapeModel[a].y,expression:"shapeModel[key].y"}],attrs:{type:"text"},domProps:{value:e.shapeModel[a].y},on:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key,"Enter"))return null;e.edit()},input:function(t){t.target.composing||e.$set(e.shapeModel[a],"y",t.target.value)}}})]:e._e(),e._v(" "),"coordinateArray"===t.type?[e._l(t.value,function(t,s){return[n("table",[n("tr",[n("td",[n("label",[e._v("x: ")]),e._v(" "),n("input",{directives:[{name:"model",rawName:"v-model",value:e.shapeModel[a][s].x,expression:"shapeModel[key][index].x"}],attrs:{type:"text"},domProps:{value:e.shapeModel[a][s].x},on:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key,"Enter"))return null;e.edit()},input:function(t){t.target.composing||e.$set(e.shapeModel[a][s],"x",t.target.value)}}})])]),e._v(" "),n("tr",[n("td",[n("label",[e._v("y: ")]),e._v(" "),n("input",{directives:[{name:"model",rawName:"v-model",value:e.shapeModel[a][s].y,expression:"shapeModel[key][index].y"}],attrs:{type:"text"},domProps:{value:e.shapeModel[a][s].y},on:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key,"Enter"))return null;e.edit()},input:function(t){t.target.composing||e.$set(e.shapeModel[a][s],"y",t.target.value)}}})])])]),e._v(" "),n("button",{on:{click:function(t){e.removeVertex(e.shapeModel[a],s)}}},[e._v("Remove")]),e._v(" "),n("br")]}),e._v(" "),n("button",{on:{click:function(t){e.addVertex(e.shapeModel[a])}}},[e._v("Add Point")])]:e._e()],2),e._v(" "),n("hr")]}),e._v(" "),n("br"),e._v(" "),n("li",{staticClass:"confirm",on:{click:function(t){e.edit()}}},[e._v("\n      Apply Changes\n    ")])],2)]):e._e()},staticRenderFns:[]};var ee=n("VU/8")(Q,K,!1,function(e){n("H9m8")},"data-v-4f1cc17e",null).exports,te={name:"ElementEditMenu",data:function(){return{selectedElement:null,visible:!0}},methods:{updateElementEdit:function(e){this.selectedElement=e,this.visible=this.selectedElement.visible},toggleVisibility:function(){this.visible=!this.visible,de.$emit("editElement",{elementID:this.selectedElement.id,visible:this.visible})},removeElement:function(){de.$emit("removeElement",{elementID:this.selectedElement.id}),de.$emit("pushElement",null)}},created:function(){var e=this;de.$on("pushElement",function(t){return e.updateElementEdit(t)})}},ne={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return null!==e.selectedElement&&void 0!==e.selectedElement?n("div",{staticClass:"shape-edit-form"},[n("ul",[n("li",{staticClass:"selected-dark"},[e._v("\n      Element ID: "+e._s(e.selectedElement.id)+"\n    ")]),e._v(" "),n("hr"),e._v(" "),n("li",{staticClass:"clickable",on:{click:function(t){e.toggleVisibility()}}},[e._v("\n      Visible\n      "),n("input",{attrs:{type:"checkbox"},domProps:{checked:e.visible}})]),e._v(" "),n("hr"),e._v(" "),n("li",{staticClass:"clickable",on:{click:function(t){e.removeElement()}}},[n("i",[e._v("Remove Element")])])])]):e._e()},staticRenderFns:[]};var ae=n("VU/8")(te,ne,!1,function(e){n("164i")},"data-v-7b4d7bae",null).exports,se={name:"CanvasEditMenu",data:function(){return{canvas:null}},methods:{updateCanvasEdit:function(e){this.canvas=e},editCanvas:function(){de.$emit("editProject",{width:this.canvas.width,height:this.canvas.height,allowTransformAttribute:this.canvas.allowTransformAttribute})},toggleAllowTransform:function(){this.canvas.allowTransformAttribute=!this.canvas.allowTransformAttribute,this.editCanvas()}},created:function(){var e=this;de.$on("response",function(t){return e.updateCanvasEdit(t.body.canvas)})}},ie={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return null!==e.canvas&&void 0!==e.canvas?n("div",{staticClass:"shape-edit-form"},[n("ul",[n("li",{staticClass:"selected-dark"},[e._v("\n      Project Settings\n    ")]),e._v(" "),n("hr"),e._v(" "),n("li",[e._v("\n      Canvas Width\n      "),n("input",{directives:[{name:"model",rawName:"v-model",value:e.canvas.width,expression:"canvas.width"}],attrs:{type:"text"},domProps:{value:e.canvas.width},on:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key,"Enter"))return null;e.editCanvas()},input:function(t){t.target.composing||e.$set(e.canvas,"width",t.target.value)}}})]),e._v(" "),n("li",[e._v("\n      Canvas Height\n      "),n("input",{directives:[{name:"model",rawName:"v-model",value:e.canvas.height,expression:"canvas.height"}],attrs:{type:"text"},domProps:{value:e.canvas.height},on:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key,"Enter"))return null;e.editCanvas()},input:function(t){t.target.composing||e.$set(e.canvas,"height",t.target.value)}}})]),e._v(" "),n("hr"),e._v(" "),n("li",{staticClass:"clickable",on:{click:function(t){e.toggleAllowTransform()}}},[e._v("\n      Allow Shape Transformations\n      "),n("input",{attrs:{type:"checkbox"},domProps:{checked:e.canvas.allowTransformAttribute}})]),e._v(" "),n("hr"),e._v(" "),n("br"),e._v(" "),n("li",{staticClass:"confirm",on:{click:function(t){e.editCanvas()}}},[e._v("\n      Apply Changes\n    ")])])]):e._e()},staticRenderFns:[]};var oe={name:"RightMenu",data:function(){return{showCanvasEditMenu:!0,showElementEditMenu:!1,showShapeEditMenu:!1,currentMenu:"canvas"}},components:{CanvasEditMenu:n("VU/8")(se,ie,!1,function(e){n("aMwM")},"data-v-3aa36a93",null).exports,ElementEditMenu:ae,ShapeEditMenuNew:ee},methods:{showOptionsForElement:function(e){this.showCanvasEditMenu=!0,null===e?(this.showElementEditMenu=!1,this.showShapeEditMenu=!1):e.hasOwnProperty("shape")?(this.showElementEditMenu=!0,this.showShapeEditMenu=!0):(this.showElementEditMenu=!0,this.showShapeEditMenu=!1)}},created:function(){var e=this;de.$on("pushElement",function(t){return e.showOptionsForElement(t)})}},le={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"right-menu"},[n("ul",{staticClass:"horizontal-list edit-menu-tabs"},[e.showCanvasEditMenu?n("li",{staticClass:"clickable",class:{"selected-dark":"canvas"===e.currentMenu},on:{click:function(t){e.currentMenu="canvas"}}},[e._v("\n      Project Settings\n    ")]):e._e(),e._v(" "),e.showElementEditMenu?n("li",{staticClass:"clickable",class:{"selected-dark":"element"===e.currentMenu},on:{click:function(t){e.currentMenu="element"}}},[e._v("\n      Element Settings\n    ")]):e._e(),e._v(" "),e.showShapeEditMenu?n("li",{staticClass:"clickable",class:{"selected-dark":"shape"===e.currentMenu},on:{click:function(t){e.currentMenu="shape"}}},[e._v("\n      Shape Attributes\n    ")]):e._e()]),e._v(" "),n("div",{staticClass:"edit-menu"},[n("CanvasEditMenu",{class:{hidden:"canvas"!==e.currentMenu}}),e._v(" "),n("ElementEditMenu",{class:{hidden:"element"!==e.currentMenu}}),e._v(" "),n("ShapeEditMenuNew",{class:{hidden:"shape"!==e.currentMenu}})],1)])},staticRenderFns:[]};var re={name:"App",components:{RightMenu:n("VU/8")(oe,le,!1,function(e){n("D9yW")},"data-v-a8b7682e",null).exports,LeftMenu:B,TopBarMenu:b,BottomBarMenu:_,Canvas:l,Connection:u}},ce={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{attrs:{id:"app"}},[t("Connection",{attrs:{"base-url":"http://localhost:8080"}}),this._v(" "),t("div",{staticClass:"app-grid"},[t("div",{staticClass:"bar-menu",attrs:{id:"top-bar-menu"}},[t("TopBarMenu")],1),this._v(" "),t("div",{attrs:{id:"left-menu"}},[t("LeftMenu")],1),this._v(" "),t("div",{attrs:{id:"canvas"}},[t("Canvas")],1),this._v(" "),t("div",{attrs:{id:"right-menu"}},[t("RightMenu")],1),this._v(" "),t("div",{staticClass:"bar-menu",attrs:{id:"bottom-bar-menu"}},[t("BottomBarMenu")],1)])],1)},staticRenderFns:[]};var ue=n("VU/8")(re,ce,!1,function(e){n("cx45")},null,null).exports;n.d(t,"dataBus",function(){return de}),a.a.use(s.a),a.a.config.productionTip=!1;var de=new a.a;new a.a({el:"#app",components:{App:ue},template:"<App/>"})},"O+wd":function(e,t){},YS5L:function(e,t){},aMwM:function(e,t){},cx45:function(e,t){},fPxB:function(e,t){},oFqF:function(e,t){},oX4Z:function(e,t){},sCLo:function(e,t){},tGao:function(e,t){},zShX:function(e,t){}},["NHnr"]);
//# sourceMappingURL=app.a103190c5af43682f4fb.js.map