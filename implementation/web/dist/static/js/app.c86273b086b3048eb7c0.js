webpackJsonp([1],{0:function(e,t){},"1BWU":function(e,t){},"2iXE":function(e,t){},CuFi:function(e,t){},D5rg:function(e,t){},Lssf:function(e,t){},NHnr:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=a("7+uW"),s=a("8+8L"),o={name:"Canvas",data:function(){return{canvas:{}}},methods:{updateCanvas:function(e){this.canvas=e}},created:function(){var e=this;J.$on("response",function(t){return e.updateCanvas(t.body.canvas)})}},i={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"canvas"},[t("div",{staticClass:"svg",domProps:{innerHTML:this._s(this.canvas.html)}})])},staticRenderFns:[]};var l=a("VU/8")(o,i,!1,function(e){a("Lssf")},"data-v-93aff0ac",null).exports,r={name:"Connection",props:{baseUrl:String},data:function(){return{projectID:""}},methods:{createCanvas:function(){var e=this;console.log('GET REQUEST TO URL="'+this.baseUrl+'/create":'),this.$http.get(this.baseUrl+"/create").then(function(t){console.log("DATA RECEIVED:"),console.log(t),e.projectID=t.body.projectID,J.$emit("response",t)})},postRequest:function(e,t){console.log('POST REQUEST TO URL="'+this.baseUrl+"/"+e+"/"+this.projectID+'":'),console.log("SENDING DATA:"),console.log(t),this.$http.post(this.baseUrl+"/"+e+"/"+this.projectID,t).then(function(e){console.log("DATA RECEIVED:"),console.log(e),J.$emit("response",e)})},downloadRequest:function(e){console.log('OPENING URL="'+this.baseUrl+"/download/"+this.projectID+'":'),window.open(this.baseUrl+"/download/"+this.projectID+"/"+e)}},created:function(){var e=this;J.$on("create",function(){return e.createCanvas()}),J.$on("editCanvas",function(t){return e.postRequest("editCanvas",t)}),J.$on("addLayer",function(t){return e.postRequest("addLayer",t)}),J.$on("editLayer",function(t){return e.postRequest("editLayer",t)}),J.$on("deleteLayer",function(t){return e.postRequest("deleteLayer",t)}),J.$on("addShape",function(t){return e.postRequest("addShape",t)}),J.$on("editShape",function(t){return e.postRequest("editShape",t)}),J.$on("transformShape",function(t){return e.postRequest("transformShape",t)}),J.$on("deleteShape",function(t){return e.postRequest("deleteShape",t)}),J.$on("download",function(t){return e.downloadRequest(t)})}},c={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"connection"},[t("h1",[this._v("Project "+this._s(this.projectID))])])},staticRenderFns:[]};var d=a("VU/8")(r,c,!1,function(e){a("t0oE")},"data-v-b7e32134",null).exports,u={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"modal-wrapper"},[t("div",{staticClass:"modal-content"},[this._t("default")],2)])},staticRenderFns:[]};var h=a("VU/8")({name:"ModalComponent"},u,!1,function(e){a("YS5L")},"data-v-7f37a05e",null).exports,p={name:"DownloadMenu",data:function(){return{fileType:"svg"}},methods:{download:function(){J.$emit("download",this.fileType),this.close()},close:function(){this.$emit("close")}}},v={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"download-menu"},[a("label",[e._v("Please select the desired image format: ")]),e._v(" "),a("select",{directives:[{name:"model",rawName:"v-model",value:e.fileType,expression:"fileType"}],on:{change:function(t){var a=Array.prototype.filter.call(t.target.options,function(e){return e.selected}).map(function(e){return"_value"in e?e._value:e.value});e.fileType=t.target.multiple?a:a[0]}}},[a("option",{attrs:{value:"svg"}},[e._v("SVG")]),e._v(" "),a("option",{attrs:{value:"png"}},[e._v("PNG")]),e._v(" "),a("option",{attrs:{value:"jpg"}},[e._v("JPG")])]),e._v(" "),a("br"),e._v(" "),a("button",{on:{click:function(t){e.download()}}},[e._v("Download image")]),e._v(" "),a("button",{on:{click:function(t){e.close()}}},[e._v("Cancel")])])},staticRenderFns:[]};var f={name:"BottomBarMenu",components:{DownloadMenu:a("VU/8")(p,v,!1,function(e){a("ak0H")},"data-v-acaf0004",null).exports,ModalComponent:h},data:function(){return{downloadMenuVisible:!1}},methods:{showDownloadMenu:function(e){this.downloadMenuVisible=e}}},y={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"bar-menu"},[a("button",{on:{click:function(t){e.showDownloadMenu(!0)}}},[e._v("Download")]),e._v(" "),a("modal-component",{directives:[{name:"show",rawName:"v-show",value:e.downloadMenuVisible,expression:"downloadMenuVisible"}]},[a("download-menu",{on:{close:function(t){e.showDownloadMenu(!1)}}})],1)],1)},staticRenderFns:[]};var m=a("VU/8")(f,y,!1,function(e){a("tGao")},"data-v-ee80d092",null).exports,_={render:function(){var e=this.$createElement;return(this._self._c||e)("div")},staticRenderFns:[]};var g=a("VU/8")({name:"TopBarMenu"},_,!1,function(e){a("oFqF")},"data-v-07f12449",null).exports,b={name:"AddShapeMenu",props:{modal:Boolean,layer:Number},data:function(){return{shapesRootPackage:"shapes"}},methods:{addShape:function(e){J.$emit("addShape",{layerIndex:this.layer,shapeClass:e}),this.modal&&this.close()},close:function(){this.$emit("close")}}},k={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"add-shape-menu"},[-1!==e.layer?a("ul",{staticClass:"shape-selection",class:{"modal-list":e.modal}},[a("li",{staticClass:"clickable",on:{click:function(t){e.addShape(e.shapesRootPackage+".Line")}}},[e._v("\n      Line\n    ")]),e._v(" "),a("li",{staticClass:"clickable",on:{click:function(t){e.addShape(e.shapesRootPackage+".Circle")}}},[e._v("\n      Circle\n    ")]),e._v(" "),a("li",{staticClass:"clickable",on:{click:function(t){e.addShape(e.shapesRootPackage+".Polygon")}}},[e._v("\n      Polygon\n    ")]),e._v(" "),a("li",{staticClass:"clickable",on:{click:function(t){e.addShape(e.shapesRootPackage+".RegularPolygon")}}},[e._v("\n      Regular Polygon\n    ")]),e._v(" "),a("li",{staticClass:"clickable",on:{click:function(t){e.addShape(e.shapesRootPackage+".Ellipse")}}},[e._v("\n      Ellipse\n    ")]),e._v(" "),a("li",{staticClass:"clickable",on:{click:function(t){e.addShape(e.shapesRootPackage+".Star")}}},[e._v("\n      Star\n    ")]),e._v(" "),a("li",{staticClass:"clickable",on:{click:function(t){e.addShape(e.shapesRootPackage+".Text")}}},[e._v("\n      Text\n    ")])]):e._e(),e._v(" "),a("hr"),e._v(" "),e.modal?a("ul",{staticClass:"modal-list"},[a("li",{staticClass:"cancel clickable",on:{click:function(t){e.close()}}},[e._v("\n      Cancel\n    ")])]):e._e()])},staticRenderFns:[]};var C={name:"LayerMenu",components:{AddShapeMenu:a("VU/8")(b,k,!1,function(e){a("X8JQ")},"data-v-05146564",null).exports,ModalComponent:h},data:function(){return{layers:[],selectedLayer:-1,selectedShape:-1,showAddShape:!1}},methods:{layerName:function(e){return-1===e?"Canvas":"Layer "+e},selectLayer:function(e){this.selectedLayer!==e&&(this.selectedLayer=e,this.selectedShape=-1===e?-1:this.layers[this.selectedLayer].shapes.length-1,this.layersUpdated())},selectShape:function(e){this.selectedShape!==e&&(this.selectedShape=e,this.layersUpdated())},toggleVisibility:function(e){this.layers[e].visible=!this.layers[e].visible,this.editLayer(e)},updateLayers:function(e){if(e.layers.length>this.layers.length)this.selectedLayer=e.layers.length-1,this.selectedShape=e.layers[this.selectedLayer].shapes.length-1;else if(e.layers.length<this.layers.length)e.layers.length<=this.selectedLayer&&(this.selectedLayer=e.layers.length-1,-1===this.selectedLayer?this.selectedShape=-1:this.selectedShape=e.layers[this.selectedLayer].shapes.length-1);else if(-1!==this.selectedLayer){var t=e.layers[this.selectedLayer].shapes.length;(t>this.layers[this.selectedLayer].shapes.length||t<=this.selectedShape)&&(this.selectedShape=e.layers[this.selectedLayer].shapes.length-1)}this.layers=e.layers,J.$emit("canvasUpdated",{width:e.width,height:e.height}),this.layersUpdated()},layersUpdated:function(){J.$emit("layersUpdated",{layers:this.layers,selectedLayer:this.selectedLayer,selectedShape:this.selectedShape})},addLayer:function(){J.$emit("addLayer",{})},editLayer:function(e){J.$emit("editLayer",{layerIndex:e,visible:this.layers[e].visible})},deleteLayer:function(){J.$emit("deleteLayer",{layerIndex:this.selectedLayer})},deleteShape:function(){J.$emit("deleteShape",{layerIndex:this.selectedLayer,shapeIndex:this.selectedShape})}},created:function(){var e=this;J.$on("response",function(t){return e.updateLayers(t.body.canvas)})}},S={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"layer-menu-grid"},[a("ul",[a("li",{staticClass:"clickable",class:{"selected-dark":-1===e.selectedLayer},on:{click:function(t){e.selectLayer(-1)}}},[a("i",[e._v(e._s(e.layerName(-1)))])]),e._v(" "),e._l(e.layers,function(t,n){return a("li",{staticClass:"clickable",class:{"selected-dark":n===e.selectedLayer},on:{click:function(t){e.selectLayer(n)}}},[e._v("\n\n      "+e._s(e.layerName(n))+"\n      "),a("input",{attrs:{type:"checkbox"},domProps:{checked:t.visible},on:{click:function(t){t.stopPropagation(),e.toggleVisibility(n)}}}),e._v(" "),n===e.selectedLayer?a("div",{staticClass:"white-border",staticStyle:{"margin-top":"20px"}},[a("ul",e._l(t.shapes,function(t,n){return a("li",{staticClass:"clickable",class:{selected:n===e.selectedShape},on:{click:function(t){t.stopPropagation(),e.selectShape(n)}}},[e._v("\n            Shape "+e._s(n)+" ("+e._s(t.shapeClass.split(".").reverse()[0])+")\n          ")])})),e._v(" "),t.shapes.length>0?a("hr"):e._e(),e._v(" "),a("ul",[a("li",{staticClass:"clickable",on:{click:function(t){t.stopPropagation(),e.showAddShape=!0}}},[a("b",[e._v("+ Add Shape")])]),e._v(" "),-1!==e.selectedShape?a("li",{staticClass:"clickable",on:{click:function(t){e.deleteShape()}}},[a("b",[e._v("– Delete Selected Shape")])]):e._e()])]):e._e()])})],2),e._v(" "),e.layers.length>0?a("hr"):e._e(),e._v(" "),a("ul",[a("li",{staticClass:"clickable",on:{click:function(t){e.addLayer()}}},[a("b",[e._v("+ Add Layer")])]),e._v(" "),-1!==e.selectedLayer?a("li",{staticClass:"clickable",on:{click:function(t){e.deleteLayer()}}},[a("b",[e._v("– Delete Selected Layer")])]):e._e()]),e._v(" "),a("ModalComponent",{directives:[{name:"show",rawName:"v-show",value:e.showAddShape,expression:"showAddShape"}]},[a("AddShapeMenu",{attrs:{modal:"",layer:e.selectedLayer},on:{close:function(t){e.showAddShape=!1}}})],1)],1)},staticRenderFns:[]};var M={name:"LeftMenu",components:{LayerMenu:a("VU/8")(C,S,!1,function(e){a("D5rg")},"data-v-74269562",null).exports}},L={render:function(){var e=this.$createElement;return(this._self._c||e)("layer-menu")},staticRenderFns:[]};var w=a("VU/8")(M,L,!1,function(e){a("2iXE")},"data-v-c3cc8a9c",null).exports,x=a("Zx67"),R=a.n(x),$=a("zwoO"),P=a.n($),D=a("Pf15"),E=a.n(D),U=a("Zrlr"),A=a.n(U),T=function e(t,a,n,s){A()(this,e),this.fillColour={label:"Fill Colour",type:"colour",value:t},this.strokeColour={label:"Stroke Colour",type:"colour",value:a},this.strokeWidth={label:"Stroke Width",type:"simple",value:n},this.opacity={label:"Opacity",type:"range:0-1",value:s}},V=function(e){function t(e,a,n,s,o,i){A()(this,t);var l=P()(this,(t.__proto__||R()(t)).call(this,n,s,o,i));return l.center={label:"Center",type:"coordinate",value:e},l.radius={label:"Radius",type:"simple",value:a},l}return E()(t,e),t}(T),N=function(e){function t(e,a,n,s,o,i,l){A()(this,t);var r=P()(this,(t.__proto__||R()(t)).call(this,a,n,s,o,i,l));return r.edgeAmount={label:"Number of Edges",type:"simple",value:e},r}return E()(t,e),t}(V),F=function(e){function t(e,a,n,s,o,i,l,r){A()(this,t);var c=P()(this,(t.__proto__||R()(t)).call(this,a,n,s,o,i,l,r));return c.radius.label="Outer Radius",c.edgeAmount.label="Number of Outer Corners",c.innerRadius={label:"Inner Radius",type:"simple",value:e},c}return E()(t,e),t}(N),I=function(e){function t(e,a,n,s,o,i,l){A()(this,t);var r=P()(this,(t.__proto__||R()(t)).call(this,s,o,i,l));return r.center={label:"Center",type:"coordinate",value:e},r.radiusX={label:"Radius X",type:"simple",value:a},r.radiusY={label:"Radius Y",type:"simple",value:n},r}return E()(t,e),t}(T),W=function(e){function t(e,a,n,s,o){A()(this,t);var i=P()(this,(t.__proto__||R()(t)).call(this,a,n,s,o));return i.coordinates={label:"Points",type:"coordinateArray",value:e},i}return E()(t,e),t}(T),q=function(e){function t(e,a,n,s,o){return A()(this,t),P()(this,(t.__proto__||R()(t)).call(this,e,a,n,s,o))}return E()(t,e),t}(W),B=function(e){function t(e,a,n,s,o,i,l,r){A()(this,t);var c=P()(this,(t.__proto__||R()(t)).call(this,o,i,l,r));return c.center={label:"Center",type:"coordinate",value:e},c.displayText={label:"Text",type:"simple",value:a},c.font={label:"Font",type:"simple",value:n},c.fontSize={label:"Font Size",type:"simple",value:s},c}return E()(t,e),t}(T),j={name:"ShapeEditMenu",data:function(){return{canvasData:{width:{label:"Canvas Width",type:"simple",value:""},height:{label:"Canvas Height",type:"simple",value:""}},shapeClass:null,shapeData:null,shapeModel:{},selectedLayer:null,selectedShape:null,shapesRootPackage:"shapes"}},methods:{updateEdit:function(e){if(this.selectedLayer=e.selectedLayer,this.selectedShape=e.selectedShape,-1===this.selectedLayer||-1===this.selectedShape)this.shapeClass=null,this.shapeModel={width:this.canvasData.width.value,height:this.canvasData.height.value},this.shapeData=this.canvasData;else{var t=e.layers[this.selectedLayer].shapes[this.selectedShape];switch(this.shapeModel=t,this.shapeClass=t.shapeClass,this.shapeClass){case this.shapesRootPackage+".Circle":this.shapeData=new V(t.center,t.radius,t.fillColour,t.strokeColour,t.strokeWidth,t.opacity);break;case this.shapesRootPackage+".RegularPolygon":this.shapeData=new N(t.edgeAmount,t.center,t.radius,t.fillColour,t.strokeColour,t.strokeWidth,t.opacity);break;case this.shapesRootPackage+".Star":this.shapeData=new F(t.innerRadius,t.edgeAmount,t.center,t.radius,t.fillColour,t.strokeColour,t.strokeWidth,t.opacity);break;case this.shapesRootPackage+".Ellipse":this.shapeData=new I(t.center,t.radiusX,t.radiusY,t.fillColour,t.strokeColour,t.strokeWidth,t.opacity);break;case this.shapesRootPackage+".Line":this.shapeData=new W(t.coordinates,t.fillColour,t.strokeColour,t.strokeWidth,t.opacity);break;case this.shapesRootPackage+".Polygon":this.shapeData=new q(t.coordinates,t.fillColour,t.strokeColour,t.strokeWidth,t.opacity);break;case this.shapesRootPackage+".Text":this.shapeData=new B(t.center,t.displayText,t.font,t.fontSize,t.fillColour,t.strokeColour,t.strokeWidth,t.opacity);break;default:console.log("Unexpected Type")}}},updateCanvasVars:function(e){this.canvasData.width={label:"Canvas Width",type:"simple",value:e.width},this.canvasData.height={label:"Canvas Height",type:"simple",value:e.height}},addVertex:function(e){e.push({x:0,y:0}),this.edit()},removeVertex:function(e,t){e.splice(t,1),this.edit()},edit:function(){-1!==this.selectedLayer&&-1!==this.selectedShape?J.$emit("editShape",{layerIndex:this.selectedLayer,shapeIndex:this.selectedShape,shape:this.shapeModel}):J.$emit("editCanvas",{width:this.shapeModel.width,height:this.shapeModel.height})}},created:function(){var e=this;J.$on("layersUpdated",function(t){return e.updateEdit(t)}),J.$on("canvasUpdated",function(t){return e.updateCanvasVars(t)})}},O={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return null!==e.shapeData&&void 0!==e.shapeData?a("div",{staticClass:"shape-edit-form"},[a("ul",[e._l(e.shapeData,function(t,n){return[a("li",[a("label",{staticClass:"header-label"},[e._v(e._s(t.label))]),a("br"),e._v(" "),"simple"===t.type?[a("input",{directives:[{name:"model",rawName:"v-model",value:e.shapeModel[n],expression:"shapeModel[key]"}],attrs:{type:"text"},domProps:{value:e.shapeModel[n]},on:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key,"Enter"))return null;e.edit()},input:function(t){t.target.composing||e.$set(e.shapeModel,n,t.target.value)}}})]:e._e(),e._v(" "),"range:0-1"===t.type?[a("input",{directives:[{name:"model",rawName:"v-model",value:e.shapeModel[n],expression:"shapeModel[key]"}],attrs:{type:"range",min:"0",max:"1",step:"any"},domProps:{value:e.shapeModel[n]},on:{change:function(t){e.edit()},__r:function(t){e.$set(e.shapeModel,n,t.target.value)}}})]:e._e(),e._v(" "),"colour"===t.type?[a("input",{directives:[{name:"model",rawName:"v-model",value:e.shapeModel[n].rgbColour,expression:"shapeModel[key].rgbColour"}],attrs:{type:"color"},domProps:{value:e.shapeModel[n].rgbColour},on:{change:function(t){e.edit()},input:function(t){t.target.composing||e.$set(e.shapeModel[n],"rgbColour",t.target.value)}}}),a("br"),e._v(" "),a("label",[e._v("Opacity ")]),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.shapeModel[n].opacity,expression:"shapeModel[key].opacity"}],attrs:{type:"range",min:"0",max:"1",step:"any"},domProps:{value:e.shapeModel[n].opacity},on:{change:function(t){e.edit()},__r:function(t){e.$set(e.shapeModel[n],"opacity",t.target.value)}}})]:e._e(),e._v(" "),"coordinate"===t.type?[a("label",[e._v("x: ")]),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.shapeModel[n].x,expression:"shapeModel[key].x"}],attrs:{type:"text"},domProps:{value:e.shapeModel[n].x},on:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key,"Enter"))return null;e.edit()},input:function(t){t.target.composing||e.$set(e.shapeModel[n],"x",t.target.value)}}}),a("br"),e._v(" "),a("label",[e._v("y: ")]),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.shapeModel[n].y,expression:"shapeModel[key].y"}],attrs:{type:"text"},domProps:{value:e.shapeModel[n].y},on:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key,"Enter"))return null;e.edit()},input:function(t){t.target.composing||e.$set(e.shapeModel[n],"y",t.target.value)}}})]:e._e(),e._v(" "),"coordinateArray"===t.type?[e._l(t.value,function(t,s){return[a("table",[a("tr",[a("td",[a("label",[e._v("x: ")]),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.shapeModel[n][s].x,expression:"shapeModel[key][index].x"}],attrs:{type:"text"},domProps:{value:e.shapeModel[n][s].x},on:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key,"Enter"))return null;e.edit()},input:function(t){t.target.composing||e.$set(e.shapeModel[n][s],"x",t.target.value)}}})])]),e._v(" "),a("tr",[a("td",[a("label",[e._v("y: ")]),e._v(" "),a("input",{directives:[{name:"model",rawName:"v-model",value:e.shapeModel[n][s].y,expression:"shapeModel[key][index].y"}],attrs:{type:"text"},domProps:{value:e.shapeModel[n][s].y},on:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key,"Enter"))return null;e.edit()},input:function(t){t.target.composing||e.$set(e.shapeModel[n][s],"y",t.target.value)}}})])])]),e._v(" "),a("button",{on:{click:function(t){e.removeVertex(e.shapeModel[n],s)}}},[e._v("Remove")]),e._v(" "),a("br")]}),e._v(" "),a("button",{on:{click:function(t){e.addVertex(e.shapeModel[n])}}},[e._v("Add Point")])]:e._e()],2),e._v(" "),a("hr")]})],2),e._v(" "),a("br"),e._v(" "),a("button",{on:{click:function(t){e.edit()}}},[e._v("Apply Changes")])]):e._e()},staticRenderFns:[]};var X={name:"RightMenu",components:{ShapeEditMenu:a("VU/8")(j,O,!1,function(e){a("1BWU")},"data-v-0bf7b6a4",null).exports}},G={render:function(){var e=this.$createElement;return(this._self._c||e)("ShapeEditMenu")},staticRenderFns:[]};var H={name:"App",components:{RightMenu:a("VU/8")(X,G,!1,function(e){a("Xp+T")},"data-v-767f651a",null).exports,LeftMenu:w,TopBarMenu:g,BottomBarMenu:m,Canvas:l,Connection:d},mounted:function(){J.$emit("create")}},Y={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{attrs:{id:"app"}},[t("Connection",{attrs:{"base-url":"http://localhost:8080"}}),this._v(" "),t("div",{staticClass:"app-grid"},[t("div",{staticClass:"bar-menu",attrs:{id:"top-bar-menu"}},[t("TopBarMenu")],1),this._v(" "),t("div",{attrs:{id:"left-menu"}},[t("LeftMenu")],1),this._v(" "),t("div",{attrs:{id:"canvas"}},[t("Canvas")],1),this._v(" "),t("div",{attrs:{id:"right-menu"}},[t("RightMenu")],1),this._v(" "),t("div",{staticClass:"bar-menu",attrs:{id:"bottom-bar-menu"}},[t("BottomBarMenu")],1)])],1)},staticRenderFns:[]};var z=a("VU/8")(H,Y,!1,function(e){a("CuFi")},null,null).exports;a.d(t,"dataBus",function(){return J}),n.a.use(s.a),n.a.config.productionTip=!1;var J=new n.a;new n.a({el:"#app",components:{App:z},template:"<App/>"})},X8JQ:function(e,t){},"Xp+T":function(e,t){},YS5L:function(e,t){},ak0H:function(e,t){},oFqF:function(e,t){},t0oE:function(e,t){},tGao:function(e,t){}},["NHnr"]);
//# sourceMappingURL=app.c86273b086b3048eb7c0.js.map