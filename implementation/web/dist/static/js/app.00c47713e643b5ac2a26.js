webpackJsonp([1], {
  0: function (e, t) {
  }, NHnr: function (e, t, a) {
    "use strict";
    Object.defineProperty(t, "__esModule", {value: !0});
    var n = a("7+uW"), s = a("8+8L"), i = {
      name: "Canvas", data: function () {
        return {canvas: {}}
      }, methods: {
        updateCanvas: function (e) {
          this.canvas = e
        }
      }, created: function () {
        var e = this;
        N.$on("response", function (t) {
          return e.updateCanvas(t.body.canvas)
        })
      }
    }, l = {
      render: function () {
        var e = this.$createElement, t = this._self._c || e;
        return t("div", {staticClass: "canvas"}, [t("div", {
          staticClass: "svg",
          domProps: {innerHTML: this._s(this.canvas.html)}
        })])
      }, staticRenderFns: []
    };
    var o = a("VU/8")(i, l, !1, function (e) {
      a("V4Ig")
    }, "data-v-7aee994c", null).exports, r = {
      name: "Connection", props: {baseUrl: String}, data: function () {
        return {projectID: ""}
      }, methods: {
        createCanvas: function () {
          var e = this;
          this.$http.get(this.baseUrl + "/test").then(function (t) {
            console.log('GET REQUEST TO URL="' + e.baseUrl + '/test":'), console.log(t), e.projectID = t.body.projectID, N.$emit("response", t)
          })
        }, postRequest: function (e, t) {
          var a = this;
          this.$http.post(this.baseUrl + "/" + e + "/" + this.projectID, t).then(function (n) {
            console.log('POST REQUEST TO URL="' + a.baseUrl + "/" + e + "/" + a.projectID + '":'), console.log("DATA SENT:"), console.log(t), console.log("DATA RECEIVED:"), console.log(n), N.$emit("response", n)
          })
        }
      }, created: function () {
        var e = this;
        this.createCanvas(), N.$on("addLayer", function (t) {
          return e.postRequest("addLayer", t)
        }), N.$on("editLayer", function (t) {
          return e.postRequest("editLayer", t)
        }), N.$on("deleteLayer", function (t) {
          return e.postRequest("deleteLayer", t)
        }), N.$on("addShape", function (t) {
          return e.postRequest("addShape", t)
        }), N.$on("editShape", function (t) {
          return e.postRequest("editShape", t)
        }), N.$on("transformShape", function (t) {
          return e.postRequest("transformShape", t)
        }), N.$on("deleteShape", function (t) {
          return e.postRequest("deleteShape", t)
        })
      }
    }, d = {
      render: function () {
        var e = this.$createElement, t = this._self._c || e;
        return t("div", {staticClass: "connection"}, [t("h1", [this._v("Project " + this._s(this.projectID))])])
      }, staticRenderFns: []
    };
    var c = a("VU/8")(r, d, !1, function (e) {
      a("NjMY")
    }, "data-v-cb7d9b28", null).exports, u = {
      name: "LayerMenu", data: function () {
        return {layers: [], selectedLayer: -1, selectedShape: -1}
      }, methods: {
        layerName: function (e) {
          return -1 === e ? "Canvas" : "Layer " + e
        }, selectLayer: function (e) {
          this.selectedLayer = e, this.selectedShape = -1 === e ? -1 : this.layers[this.selectedLayer].shapes.length - 1, this.layersUpdated()
        }, selectShape: function (e) {
          this.selectedShape = e, this.layersUpdated()
        }, updateLayers: function (e) {
          this.layers = e.layers, this.layersUpdated()
        }, layersUpdated: function () {
          N.$emit("layersUpdated", {
            layers: this.layers,
            selectedLayer: this.selectedLayer,
            selectedShape: this.selectedShape
          })
        }, addLayer: function () {
          N.$emit("addLayer", {})
        }, deleteLayer: function () {
          N.$emit("deleteLayer", {layerIndex: this.selectedLayer})
        }, deleteShape: function () {
          N.$emit("deleteShape", {layerIndex: this.selectedLayer, shapeIndex: this.selectedShape})
        }
      }, created: function () {
        var e = this;
        N.$on("response", function (t) {
          return e.updateLayers(t.body.canvas)
        })
      }
    }, p = {
      render: function () {
        var e = this, t = e.$createElement, a = e._self._c || t;
        return a("div", {staticClass: "layer-menu-grid"}, [a("div", {staticClass: "layer-menu-shapes"}, [-1 !== e.selectedLayer ? a("ul", [e._l(e.layers[e.selectedLayer].shapes, function (t, n) {
          return a("li", {
            class: {selected: n === e.selectedShape}, on: {
              click: function (t) {
                e.selectShape(n)
              }
            }
          }, [e._v("\n        Shape " + e._s(n) + " (" + e._s(t.shape) + ")\n      ")])
        }), e._v(" "), -1 !== e.selectedShape ? a("li", {
          on: {
            click: function (t) {
              e.deleteShape()
            }
          }
        }, [e._v("\n        - Delete Selected Shape\n      ")]) : e._e()], 2) : e._e()]), e._v(" "), a("div", {staticClass: "layer-menu-layers"}, [a("ul", [a("li", {
          class: {selected: -1 === e.selectedLayer},
          on: {
            click: function (t) {
              e.selectLayer(-1)
            }
          }
        }, [e._v("\n        " + e._s(e.layerName(-1)) + "\n      ")]), e._v(" "), e._l(e.layers, function (t, n) {
          return a("li", {
            class: {selected: n === e.selectedLayer}, on: {
              click: function (t) {
                e.selectLayer(n)
              }
            }
          }, [e._v("\n        " + e._s(e.layerName(n)) + "\n      ")])
        }), e._v(" "), a("li", {
          on: {
            click: function (t) {
              e.addLayer()
            }
          }
        }, [e._v("\n        + Add Layer\n      ")]), e._v(" "), -1 !== e.selectedLayer ? a("li", {
          on: {
            click: function (t) {
              e.deleteLayer()
            }
          }
        }, [e._v("\n        - Delete Selected Layer\n      ")]) : e._e()], 2)])])
      }, staticRenderFns: []
    };
    var h = a("VU/8")(u, p, !1, function (e) {
        a("bLf1")
      }, "data-v-4e1ad844", null).exports, v = a("Zx67"), y = a.n(v), f = a("zwoO"), _ = a.n(f), m = a("Pf15"),
      k = a.n(m), b = a("Zrlr"), S = a.n(b), C = function e(t, a, n, s) {
        S()(this, e), this.fillColour = {
          label: "Fill Colour",
          type: "colour",
          value: t
        }, this.strokeColour = {
          label: "Stroke Colour",
          type: "colour",
          value: a
        }, this.strokeWidth = {label: "Stroke Width", type: "simple", value: n}, this.opacity = {
          label: "Opacity",
          type: "simple",
          value: s
        }
      }, L = function (e) {
        function t(e, a, n, s, i, l) {
          S()(this, t);
          var o = _()(this, (t.__proto__ || y()(t)).call(this, n, s, i, l));
          return o.center = {label: "Center", type: "coordinate", value: e}, o.radius = {
            label: "Radius",
            type: "simple",
            value: a
          }, o
        }

        return k()(t, e), t
      }(C), g = function (e) {
        function t(e, a, n, s, i, l, o) {
          S()(this, t);
          var r = _()(this, (t.__proto__ || y()(t)).call(this, a, n, s, i, l, o));
          return r.edgeAmount = {label: "Number of Edges", type: "simple", value: e}, r
        }

        return k()(t, e), t
      }(L), x = function (e) {
        function t(e, a, n, s, i, l, o, r) {
          S()(this, t);
          var d = _()(this, (t.__proto__ || y()(t)).call(this, a, n, s, i, l, o, r));
          return d.radius.label = "Outer Radius", d.edgeAmount.label = "Number of Outer Corners", d.innerRadius = {
            label: "Inner Radius",
            type: "simple",
            value: e
          }, d
        }

        return k()(t, e), t
      }(g), M = function (e) {
        function t(e, a, n, s, i, l, o) {
          S()(this, t);
          var r = _()(this, (t.__proto__ || y()(t)).call(this, s, i, l, o));
          return r.center = {label: "Center", type: "coordinate", value: e}, r.radiusX = {
            label: "Radius X",
            type: "simple",
            value: a
          }, r.radiusY = {label: "Radius Y", type: "simple", value: n}, r
        }

        return k()(t, e), t
      }(C), $ = function (e) {
        function t(e, a, n, s, i) {
          S()(this, t);
          var l = _()(this, (t.__proto__ || y()(t)).call(this, a, n, s, i));
          return l.coordinates = {label: "Points", type: "coordinateArray", value: e}, l
        }

        return k()(t, e), t
      }(C), D = function (e) {
        function t(e, a, n, s, i) {
          return S()(this, t), _()(this, (t.__proto__ || y()(t)).call(this, e, a, n, s, i))
        }

        return k()(t, e), t
      }($), E = function (e) {
        function t(e, a, n, s, i, l, o, r) {
          S()(this, t);
          var d = _()(this, (t.__proto__ || y()(t)).call(this, i, l, o, r));
          return d.center = {label: "Center", type: "coordinate", value: e}, d.displayText = {
            label: "Text",
            type: "simple",
            value: a
          }, d.font = {label: "Font", type: "simple", value: n}, d.fontSize = {
            label: "Font Size",
            type: "simple",
            value: s
          }, d
        }

        return k()(t, e), t
      }(C), R = {
        name: "ShapeEditForm", data: function () {
          return {shapeData: null, shapeModel: null, selectedLayer: null, selectedShape: null}
        }, methods: {
          addVertex: function (e) {
            e.push({x: 0, y: 0}), this.edit()
          }, removeVertex: function (e, t) {
            e.splice(t, 1), this.edit()
          }, edit: function () {
            N.$emit("editShape", {layerIndex: this.selectedLayer, shapeIndex: this.selectedShape, shape: this.shapeModel})
          }
        }, created: function () {
          var e = this;
          N.$on("setShapeEditForm", function (t) {
            e.shapeData = t.shapeData, e.shapeModel = t.shapeModel, e.selectedLayer = t.selectedLayer, e.selectedShape = t.selectedShape
          })
        }
      }, U = {
        render: function () {
          var e = this, t = e.$createElement, a = e._self._c || t;
          return null !== e.shapeData ? a("div", {staticClass: "shape-edit-form"}, [e._l(e.shapeData, function (t, n) {
            return [a("label", {staticClass: "header-label"}, [e._v(e._s(t.label))]), a("br"), e._v(" "), "simple" === t.type ? [a("input", {
              directives: [{
                name: "model",
                rawName: "v-model",
                value: e.shapeModel[n],
                expression: "shapeModel[key]"
              }], attrs: {type: "text"}, domProps: {value: e.shapeModel[n]}, on: {
                keyup: function (t) {
                  if (!("button" in t) && e._k(t.keyCode, "enter", 13, t.key, "Enter")) return null;
                  e.edit()
                }, input: function (t) {
                  t.target.composing || e.$set(e.shapeModel, n, t.target.value)
                }
              }
            })] : e._e(), e._v(" "), "colour" === t.type ? [a("input", {
              directives: [{
                name: "model",
                rawName: "v-model",
                value: e.shapeModel[n],
                expression: "shapeModel[key]"
              }], attrs: {type: "text"}, domProps: {value: e.shapeModel[n]}, on: {
                keyup: function (t) {
                  if (!("button" in t) && e._k(t.keyCode, "enter", 13, t.key, "Enter")) return null;
                  e.edit()
                }, input: function (t) {
                  t.target.composing || e.$set(e.shapeModel, n, t.target.value)
                }
              }
            })] : e._e(), e._v(" "), "coordinate" === t.type ? [a("label", [e._v("x: ")]), e._v(" "), a("input", {
              directives: [{
                name: "model",
                rawName: "v-model",
                value: e.shapeModel[n].x,
                expression: "shapeModel[key].x"
              }], attrs: {type: "text"}, domProps: {value: e.shapeModel[n].x}, on: {
                keyup: function (t) {
                  if (!("button" in t) && e._k(t.keyCode, "enter", 13, t.key, "Enter")) return null;
                  e.edit()
                }, input: function (t) {
                  t.target.composing || e.$set(e.shapeModel[n], "x", t.target.value)
                }
              }
            }), a("br"), e._v(" "), a("label", [e._v("y: ")]), e._v(" "), a("input", {
              directives: [{
                name: "model",
                rawName: "v-model",
                value: e.shapeModel[n].y,
                expression: "shapeModel[key].y"
              }], attrs: {type: "text"}, domProps: {value: e.shapeModel[n].y}, on: {
                keyup: function (t) {
                  if (!("button" in t) && e._k(t.keyCode, "enter", 13, t.key, "Enter")) return null;
                  e.edit()
                }, input: function (t) {
                  t.target.composing || e.$set(e.shapeModel[n], "y", t.target.value)
                }
              }
            })] : e._e(), e._v(" "), "coordinateArray" === t.type ? [e._l(t.value, function (t, s) {
              return [a("label", [e._v(e._s(s + 1) + " ")]), a("button", {
                on: {
                  click: function (t) {
                    e.removeVertex(e.shapeModel[n], s)
                  }
                }
              }, [e._v("Remove")]), a("br"), e._v(" "), a("label", [e._v("x: ")]), e._v(" "), a("input", {
                directives: [{
                  name: "model",
                  rawName: "v-model",
                  value: e.shapeModel[n][s].x,
                  expression: "shapeModel[key][index].x"
                }], attrs: {type: "text"}, domProps: {value: e.shapeModel[n][s].x}, on: {
                  keyup: function (t) {
                    if (!("button" in t) && e._k(t.keyCode, "enter", 13, t.key, "Enter")) return null;
                    e.edit()
                  }, input: function (t) {
                    t.target.composing || e.$set(e.shapeModel[n][s], "x", t.target.value)
                  }
                }
              }), a("br"), e._v(" "), a("label", [e._v("y: ")]), e._v(" "), a("input", {
                directives: [{
                  name: "model",
                  rawName: "v-model",
                  value: e.shapeModel[n][s].y,
                  expression: "shapeModel[key][index].y"
                }], attrs: {type: "text"}, domProps: {value: e.shapeModel[n][s].y}, on: {
                  keyup: function (t) {
                    if (!("button" in t) && e._k(t.keyCode, "enter", 13, t.key, "Enter")) return null;
                    e.edit()
                  }, input: function (t) {
                    t.target.composing || e.$set(e.shapeModel[n][s], "y", t.target.value)
                  }
                }
              }), a("br")]
            }), e._v(" "), a("button", {
              on: {
                click: function (t) {
                  e.addVertex(e.shapeModel[n])
                }
              }
            }, [e._v("Add Point")])] : e._e(), e._v(" "), a("hr")]
          })], 2) : e._e()
        }, staticRenderFns: []
      };
    var w = {
      name: "ShapeMenu", components: {
        ShapeEditForm: a("VU/8")(R, U, !1, function (e) {
          a("UcIm")
        }, "data-v-345ccff6", null).exports
      }, data: function () {
        return {
          canvasData: {},
          shapeType: null,
          shapeData: null,
          shapeModel: {},
          selectedLayer: null,
          selectedShape: null
        }
      }, methods: {
        updateEdit: function (e) {
          if (this.selectedLayer = e.selectedLayer, this.selectedShape = e.selectedShape, -1 === this.selectedLayer || -1 === this.selectedShape) this.shapeType = null, this.shapeModel = {
            width: this.canvasData.width.value,
            height: this.canvasData.height.value
          }, this.shapeData = this.canvasData; else {
            var t = e.layers[this.selectedLayer].shapes[this.selectedShape];
            switch (this.shapeModel = t, this.shapeType = t.shape, this.shapeType) {
              case"Circle":
                this.shapeData = new L(t.center, t.radius, t.fillColour, t.strokeColour, t.strokeWidth, t.opacity);
                break;
              case"RegularPolygon":
                this.shapeData = new g(t.edgeAmount, t.center, t.radius, t.fillColour, t.strokeColour, t.strokeWidth, t.opacity);
                break;
              case"Star":
                this.shapeData = new x(t.innerRadius, t.edgeAmount, t.center, t.radius, t.fillColour, t.strokeColour, t.strokeWidth, t.opacity);
                break;
              case"Ellipse":
                this.shapeData = new M(t.center, t.radiusX, t.radiusY, t.fillColour, t.strokeColour, t.strokeWidth, t.opacity);
                break;
              case"Line":
                this.shapeData = new $(t.coordinates, t.fillColour, t.strokeColour, t.strokeWidth, t.opacity);
                break;
              case"Polygon":
                this.shapeData = new D(t.coordinates, t.fillColour, t.strokeColour, t.strokeWidth, t.opacity);
                break;
              case"Text":
                this.shapeData = new E(t.center, t.displayText, t.font, t.fontSize, t.fillColour, t.strokeColour, t.strokeWidth, t.opacity);
                break;
              default:
                console.log("Unexpected Type")
            }
          }
          N.$emit("setShapeEditForm", {
            shapeData: this.shapeData,
            shapeModel: this.shapeModel,
            selectedLayer: this.selectedLayer,
            selectedShape: this.selectedShape
          })
        }, updateCanvasVars: function (e) {
          this.canvasData.width = {
            label: "Canvas Width",
            type: "simple",
            value: e.width
          }, this.canvasData.height = {label: "Canvas Height", type: "simple", value: e.height}
        }, addShape: function (e) {
          N.$emit("addShape", {layerIndex: this.selectedLayer, shapeType: e})
        }
      }, created: function () {
        var e = this;
        N.$on("layersUpdated", function (t) {
          return e.updateEdit(t)
        }), N.$on("response", function (t) {
          return e.updateCanvasVars(t.body.canvas)
        })
      }
    }, A = {
      render: function () {
        var e = this, t = e.$createElement, a = e._self._c || t;
        return a("div", {staticClass: "shape-menu-grid"}, [a("div", {staticClass: "shape-menu-edit"}, [a("ShapeEditForm")], 1), e._v(" "), a("div", {staticClass: "shape-menu-shapes"}, [a("ul", [a("li", {
          on: {
            click: function (t) {
              e.addShape("Line")
            }
          }
        }, [e._v("\n        + Add Line\n      ")]), e._v(" "), a("li", {
          on: {
            click: function (t) {
              e.addShape("Circle")
            }
          }
        }, [e._v("\n        + Add Circle\n      ")]), e._v(" "), a("li", {
          on: {
            click: function (t) {
              e.addShape("Polygon")
            }
          }
        }, [e._v("\n        + Add Polygon\n      ")]), e._v(" "), a("li", {
          on: {
            click: function (t) {
              e.addShape("Regular Polygon")
            }
          }
        }, [e._v("\n        + Add Regular Polygon\n      ")]), e._v(" "), a("li", {
          on: {
            click: function (t) {
              e.addShape("Ellipse")
            }
          }
        }, [e._v("\n        + Add Ellipse\n      ")]), e._v(" "), a("li", {
          on: {
            click: function (t) {
              e.addShape("Star")
            }
          }
        }, [e._v("\n        + Add Star\n      ")]), e._v(" "), a("li", {
          on: {
            click: function (t) {
              e.addShape("Text")
            }
          }
        }, [e._v("\n        + Add Text\n      ")])])])])
      }, staticRenderFns: []
    };
    var T = {
      name: "App", components: {
        ShapeMenu: a("VU/8")(w, A, !1, function (e) {
          a("qA9b")
        }, "data-v-0e6cd1ef", null).exports, Canvas: o, Connection: c, LayerMenu: h
      }
    }, P = {
      render: function () {
        var e = this.$createElement, t = this._self._c || e;
        return t("div", {attrs: {id: "app"}}, [t("Connection", {attrs: {"base-url": "http://localhost:8080"}}), this._v(" "), t("div", {staticClass: "app-grid"}, [t("div", {attrs: {id: "layer-menu"}}, [t("LayerMenu")], 1), this._v(" "), t("div", {attrs: {id: "canvas"}}, [t("Canvas")], 1), this._v(" "), t("div", {attrs: {id: "shape-menu"}}, [t("ShapeMenu")], 1)])], 1)
      }, staticRenderFns: []
    };
    var I = a("VU/8")(T, P, !1, function (e) {
      a("wKUK")
    }, null, null).exports;
    a.d(t, "dataBus", function () {
      return N
    }), n.a.use(s.a), n.a.config.productionTip = !1;
    var N = new n.a;
    new n.a({el: "#app", components: {App: I}, template: "<App/>"})
  }, NjMY: function (e, t) {
  }, UcIm: function (e, t) {
  }, V4Ig: function (e, t) {
  }, bLf1: function (e, t) {
  }, qA9b: function (e, t) {
  }, wKUK: function (e, t) {
  }
}, ["NHnr"]);
//# sourceMappingURL=app.00c47713e643b5ac2a26.js.map
