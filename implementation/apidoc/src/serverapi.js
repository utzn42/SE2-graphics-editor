/**
 * @apiDefine ServerResponse
 * @apiSuccess {String} projectID ID of the project.
 * @apiSuccess {Object} canvas Project canvas including all SVG elements.
 * @apiSuccess {Number} canvas.width Width of project canvas.
 * @apiSuccess {Number} canvas.height Height of project canvas.
 * @apiSuccess {Boolean} canvas.allowTransformAttribute If false, disallows HTML "transform" attributes for the entire canvas.
 * @apiSuccess {String} canvas.html The canvas as HTML SVG object.
 * @apiSuccess {Object[]} canvas.elements The list of elements on the canvas.
 */

/**
 * @apiDefine ErrorResponse
 * @apiError {String} timestamp Time the error occurred.
 * @apiError {Number} status HTML error code.
 * @apiError {String} error HTML error name.
 * @apiError {String} message Error message.
 * @apiError {String} path URL of the request that caused the error.
 */


/**
 * @api {get} /create Create project
 * @apiName CreateProject
 * @apiGroup Project
 * @apiDescription Creates a new project and returns it.
 *
 * @apiUse ServerResponse
 *
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *     {
 *       "projectID": "cd2662",
 *       "canvas": {
 *         "width": 200,
 *         "height": 200,
 *         "allowTransformAttribute": true,
 *         "html": "<svg width="200.0" height="200.0" baseProfile="full" xmlns="http://www.w3.org/2000/svg"></svg>",
 *         "elements": [],
 *       }
 *     }
 *
 * @apiUse ErrorResponse
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 405 Method Not Allowed
 *     {
 *       "timestamp": "2019-01-13 10:47:51.98",
 *       "status": 405,
 *       "error": "Method Not Allowed",
 *       "message": "Request method 'POST' not supported",
 *       "path": "/create"
 *     }
 *
 */

/**
 * @api {get} /:projectID Load project
 * @apiName LoadProject
 * @apiGroup Project
 * @apiDescription Returns the project with the specified ID if it exists.
 *
 * @apiParam {Number} projectID ID of the project to load.
 *
 * @apiParamExample {json} Request-Example:
 *     GET /cd2662
 *
 * @apiUse ServerResponse
 *
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *     {
 *       "projectID": "cd2662",
 *       "canvas": {
 *         "width": 200,
 *         "height": 200,
 *         "allowTransformAttribute": true,
 *         "html": "<svg width="200.0" height="200.0" baseProfile="full" xmlns="http://www.w3.org/2000/svg"></svg>",
 *         "elements": [],
 *       }
 *     }
 *
 * @apiUse ErrorResponse
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 500 Internal Server Error
 *     {
 *       "timestamp": "2019-01-13 11:56:49.65",
 *       "status": 500,
 *       "error": "Internal Server Error",
 *       "message": "Project ID abcdef does not exist!",
 *       "path": "/abcdef"
 *     }
 *
 */


/**
 * @api {post} /:projectID/editProject Edit project
 * @apiName EditProject
 * @apiGroup Project
 * @apiDescription Allows modification of project properties.
 *
 * @apiParam {Number} projectID (URL param) ID of the working project.
 * @apiParam {Number} [width] New canvas width.
 * @apiParam {Number} [height] New canvas height.
 * @apiParam {Boolean} [allowTransformAttribute] If false, disallows HTML "transform" attributes for the entire canvas.
 *
 * @apiParamExample {json} Request-Example:
 *     POST /8005f0/editProject
 *     {
 *       "width": 800,
 *       "allowTransformAttribute": false
 *     }
 *
 * @apiUse ServerResponse
 *
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *     {
 *       "projectID": "8005f0",
 *       "canvas": {
 *         "width": 800,
 *         "height": 200,
 *         "allowTransformAttribute": false,
 *         "html": "<svg width="800.0" height="200.0" baseProfile="full" xmlns="http://www.w3.org/2000/svg"></svg>",
 *         "elements": [],
 *       }
 *     }
 *
 * @apiUse ErrorResponse
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 500 Internal Server Error
 *     {
 *       "timestamp": "2019-01-13 13:01:46.189",
 *       "status": 500,
 *       "error": "Internal Server Error",
 *       "message": "Canvas width and height must both be positive!",
 *       "path": "/8005f0/editProject"
 *     }
 *
 */


/**
 * @api {post} /:projectID/addLayerGroup Add layer group
 * @apiName AddLayerGroup
 * @apiGroup ProjectElements
 * @apiDescription Creates a layer group that can hold any number of elements.
 * The position at which to create the layer group can be specified with the parameters.
 * The placeIntoElementID parameter will always override placeBeforeElementID if both are given.
 * If none are given, will insert the layer group at the very last position.
 *
 * @apiParam {Number} projectID ID of the working project.
 * @apiParam {Number} [placeBeforeElementID] The ID of the element to place the new layer group before.
 * @apiParam {Number} [placeIntoElementID] The ID of the element to place the new layer group in. Will cause errors if that element is not a layer group itself.
 *
 * @apiParamExample {json} Request-Example:
 *     POST /cd04a4/addLayerGroup
 *     {
 *       "placeIntoElementID": 0
 *     }
 *
 * @apiUse ServerResponse
 *
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *     {
 *       "projectID": "cd04a4",
 *       "canvas": {
 *         "width": 200,
 *         "height": 200,
 *         "allowTransformAttribute": true,
 *         "html": "<svg width="200.0" height="200.0" baseProfile="full" xmlns="http://www.w3.org/2000/svg"></svg>",
 *         "elements": [
 *           {
 *             "elementType": "LAYER_GROUP",
 *             "id": 0,
 *             "visible": true,
 *             "elements": [
 *               {
 *                 "elementType": "LAYER_GROUP",
 *                 "id": 2,
 *                 "visible": true,
 *                 "elements": [],
 *               }
 *             ],
 *           },
 *           {
 *             "elementType": "LAYER_GROUP",
 *             "id": 1,
 *             "visible": true,
 *             "elements": [],
 *           }
 *         ],
 *       }
 *     }
 *
 * @apiUse ErrorResponse
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 500 Internal Server Error
 *     {
 *       "timestamp": "2019-01-13 12:37:38.555",
 *       "status": 500,
 *       "error": "Internal Server Error",
 *       "message": "Cannot add into element 0; Not a layer group!",
 *       "path": "/af5570/addLayerGroup"
 *     }
 *
 */

/**
 * @api {post} /:projectID/addShape Add shape
 * @apiName AddShape
 * @apiGroup ProjectElements
 * @apiDescription Creates a layer with the given shape type.
 * The position at which to create the layer can be specified with the parameters.
 * The placeIntoElementID parameter will always override placeBeforeElementID if both are given.
 * If none are given, will insert the layer at the very last position.
 *
 * @apiParam {Number} projectID ID of the working project.
 * @apiParam {String="CIRCLE","ELLIPSE","LINE","POLYGON","REGULAR_POLYGON","STAR","TEXT"} shapeType The type of shape to add.
 * @apiParam {Number} [placeBeforeElementID] The ID of the element to place the new layer before.
 * @apiParam {Number} [placeIntoElementID] The ID of the element to place the new layer in. Will cause errors if that element is not a layer group.
 *
 * @apiParamExample {json} Request-Example:
 *     POST /cd04a4/addShape
 *     {
 *       "shapeType": "CIRCLE",
 *       "placeIntoElementID": 0
 *     }
 *
 * @apiUse ServerResponse
 *
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *     {
 *       "projectID": "cd04a4",
 *       "canvas": {
 *         "width": 200,
 *         "height": 200,
 *         "allowTransformAttribute": true,
 *         "html": "<svg width="200.0" height="200.0" baseProfile="full" xmlns="http://www.w3.org/2000/svg"></svg>",
 *         "elements": [
 *           {
 *             "elementType": "GROUP_LAYER",
 *             "id": 0,
 *             "visible": true,
 *             "elements": [
 *                {
 *                  "elementType": "LAYER",
 *                  "id": 2,
 *                  "visible": true,
 *                  "shape": {
 *                    "fillColour": {
 *                      "rgbColour": "#FFFFFF",
 *                      "opacity": 0
 *                    },
 *                    "strokeColour": {
 *                      "rgbColour": "#000000",
 *                      "opacity": 1
 *                    },
 *                    "strokeWidth": 1,
 *                    "opacity": 1,
 *                    "center": {
 *                      "x": 50,
 *                      "y": 50
 *                    },
 *                    "radius": 50,
 *                    "shapeType": "CIRCLE",
 *                    "transformations": [],
 *                  }
 *                }
 *             ],
 *           },
 *           {
 *             "elementType": "LAYER_GROUP",
 *             "id": 1,
 *             "visible": true,
 *             "elements": [],
 *           }
 *         ],
 *       }
 *     }
 *
 * @apiUse ErrorResponse
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 500 Internal Server Error
 *     {
 *       "timestamp": "2019-01-13 12:37:38.555",
 *       "status": 500,
 *       "error": "Internal Server Error",
 *       "message": "Cannot add into element 0; Not a layer group!",
 *       "path": "/af5570/addShape"
 *     }
 *
 */


/**
 * @api {post} /:projectID/modifyShape Modify shape
 * @apiName ModifyShape
 * @apiGroup ProjectElements
 * @apiDescription Modifies a shape using the given attributes.
 * Possibly attributes can vary depending on the shapeType and all attributes are optional.
 * The structure of the attributes to modify has to match the structure of a shape returned by the API.
 *
 * @apiParam {Number} projectID ID of the working project.
 * @apiParam {Number} elementID ID of the element to modify.
 * @apiParam {String="CIRCLE","ELLIPSE","LINE","POLYGON","REGULAR_POLYGON","STAR","TEXT"} shapeType The type of shape to modify.
 * @apiParam {Object} [fillColour] (For all shapes) The fill colour of the shape.
 * @apiParam {String} [fillColour.rgbColour] Hex value. Takes precedence over individual colour values.
 * @apiParam {Number{0-255}} [fillColour.red] Red value.
 * @apiParam {Number{0-255}} [fillColour.green] Green value.
 * @apiParam {Number{0-255}} [fillColour.blue] Blue value.
 * @apiParam {Number{0-1}} [fillColour.opacity] Opacity.
 * @apiParam {Object} [strokeColour] (For all shapes) The stroke colour of the shape.
 * @apiParam {String} [strokeColour.rgbColour] Hex value. Takes precedence over individual colour values.
 * @apiParam {Number{0-255}} [strokeColour.red] Red value.
 * @apiParam {Number{0-255}} [strokeColour.green] Green value.
 * @apiParam {Number{0-255}} [strokeColour.blue] Blue value.
 * @apiParam {Number{0-1}} [strokeColour.opacity] Opacity.
 * @apiParam {Number} [strokeWidth] (For all shapes) The stroke width of the shape.
 * @apiParam {Number{0-1}} [opacity] (For all shapes) The opacity of the shape.
 * @apiParam {Object} [center] (For CIRCLE, ELLIPSE, REGULAR_POLYGON, STAR, TEXT) The center of the shape.
 * @apiParam {Number} [center.x] X-coordinate
 * @apiParam {Number} [center.y] Y-coordinate
 * @apiParam {Number} [radius] (For CIRCLE, REGULAR_POLYGON, STAR) The (outer) radius of the shape.
 * @apiParam {Number} [radiusX] (For ELLIPSE) The x-radius of the shape.
 * @apiParam {Number} [radiusY] (For ELLIPSE) The y-radius of the shape.
 * @apiParam {Number} [edgeAmount] (For REGULAR_POLYGON, STAR) The number of edges of the shape (or half the number of edges in case of a STAR).
 * @apiParam {Number} [innerRadius] (For STAR) The inner radius of the shape.
 * @apiParam {String} [displayText] (For TEXT) The displayed text of the shape.
 * @apiParam {String} [font] (For TEXT) The font the shape uses for its display text.
 * @apiParam {Number} [fontSize] (For TEXT) The font size the shape uses for its display text.
 *
 * @apiParamExample {json} Request-Example:
 *     POST /a3eb8d/modifyShape
 *     {
 *       "elementID": 1,
 *       "shapeType": "TEXT",
 *       "fillColour": {
 *         "hex": "#33AAFF",
 *         "opacity": 0.5
 *       },
 *       "center": {
 *         "x": 110,
 *         "y": 40.7
 *       },
 *       "displayText": "ModifiedText",
 *       "font": "Times New Roman"
 *     }
 *
 * @apiUse ServerResponse
 *
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *     {
 *       "projectID": "a3eb8d",
 *       "canvas": {
 *         "width": 200,
 *         "height": 200,
 *         "allowTransformAttribute": true,
 *         "html": "<svg width="200.0" height="200.0" baseProfile="full" xmlns="http://www.w3.org/2000/svg"><text fill="#33AAFF" fill-opacity="0.5" stroke="#000000" stroke-opacity="1.0" stroke-width="0.0" opacity="1.0" x="110.0" y="40.7" font-family="Times New Roman" font-size="12.0" text-anchor="middle" alignment-baseline="middle">ModifiedText</text></svg>",
 *         "elements": [
 *           {
 *             "elementType": "LAYER",
 *             "id": 1,
 *             "visible": true,
 *             "shape": {
 *               "fillColour": {
 *                 "rgbColour": "#33AAFF",
 *                 "opacity": 0.5
 *               },
 *               "strokeColour": {
 *                 "rgbColour": "#000000",
 *                 "opacity": 1
 *               },
 *               "strokeWidth": 0,
 *               "opacity": 1,
 *               "center": {
 *                 "x": 110,
 *                 "y": 40.7
 *               },
 *               "displayText": "ModifiedText",
 *               "font": "Times New Roman",
 *               "fontSize": 12,
 *               "shapeType": "TEXT",
 *               "transformations": [],
 *             }
 *           }
 *         ],
 *       }
 *     }
 *
 * @apiUse ErrorResponse
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 500 Internal Server Error
 *     {
 *       "timestamp": "2019-01-13 13:50:23.866",
 *       "status": 500,
 *       "error": "Internal Server Error",
 *       "message": "Element with ID 0 is not a single shape!",
 *       "path": "/a3eb8d/modifyShape"
 *     }
 *
 */


/**
 * @api {post} /:projectID/editElement Edit element
 * @apiName EditElement
 * @apiGroup ProjectElements
 * @apiDescription Allows setting the visibility of an element.
 *
 * @apiParam {Number} projectID ID of the working project.
 * @apiParam {Number} elementID ID of the element to modify.
 * @apiParam {Boolean} visible True to make element visible, false otherwise.
 *
 * @apiParamExample {json} Request-Example:
 *     POST /a3eb8d/editElement
 *     {
 *       "elementID": 0,
 *       "visible": false
 *     }
 *
 * @apiUse ServerResponse
 *
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *     {
 *       "projectID": "a3eb8d",
 *       "canvas": {
 *         "width": 200,
 *         "height": 200,
 *         "allowTransformAttribute": true,
 *         "html": "<svg width="200.0" height="200.0" baseProfile="full" xmlns="http://www.w3.org/2000/svg"></svg>",
 *         "elements": [
 *           {
 *             "elementType": "LAYER",
 *             "id": 0,
 *             "visible": false,
 *             "shape": {
 *               "fillColour": {
 *                 "rgbColour": "#FFFFFF",
 *                 "opacity": 0
 *               },
 *               "strokeColour": {
 *                 "rgbColour": "#000000",
 *                 "opacity": 1
 *               },
 *               "strokeWidth": 1,
 *               "opacity": 1,
 *               "coordinates": [
 *                 {
 *                   "x": 0,
 *                   "y": 0
 *                 },
 *                 {
 *                   "x": 100,
 *                   "y": 100
 *                 }
 *               ],
 *               "shapeType": "LINE",
 *               "transformations": [],
 *             }
 *           }
 *         ],
 *       }
 *     }
 *
 * @apiUse ErrorResponse
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 405 Method Not Allowed
 *     {
 *       "timestamp": "2019-01-13T12:21:58.022+0000",
 *       "status": 405,
 *       "error": "Method Not Allowed",
 *       "message": "Request method 'GET' not supported",
 *       "path": "/a3eb8d/editElement"
 *     }
 *
 */

/**
 * @api {post} /:projectID/transformElement Transform element
 * @apiName TransformElement
 * @apiGroup ProjectElements
 * @apiDescription Transforms an element using the given attributes.
 * Some transformations on some shapes may cause errors if allowTransformAttribute of the Project is set to false.
 * Transformations on layer groups distribute to the underlying layers.
 *
 * @apiParam {Number} projectID ID of the working project.
 * @apiParam {Number} elementID ID of the element to modify.
 * @apiParam {Boolean} [clearAll] If true, clears all transformations affecting the element.
 * @apiParam {Object} [transform] The transformation to apply to the element.
 * @apiParam {String="ROTATE", "SCALE", "SKEW", "TRANSLATE", "UNIFORM_SCALE"} [transform.transformationType] The type of transformation
 * @apiParam {Object} [transform.origin] The origin to perform the transformation in respect to.
 * @apiParam {Number} transform.origin.x X-coordinate.
 * @apiParam {Number} transform.origin.y Y-coordinate.
 * @apiParam {Number} [transform.rotationAngle] (Mandatory for ROTATION) The rotation angle.
 * @apiParam {Object} [transform.scale] (Mandatory for SCALE and UNIFORM_SCALE) The scale factors. Has to be a single Number for UNIFORM_SCALE.
 * @apiParam {Number} transform.scale.x (SCALE) X scale factor.
 * @apiParam {Number} transform.scale.y (SCALE) Y scale factor.
 * @apiParam {String="x", "y"} [transform.skewAxis] (Mandatory for SKEW) The skew axis.
 * @apiParam {Number} [transform.skewAngle] (Mandatory for SKEW) The skew angle. Perfect right angles will cause errors.
 * @apiParam {Object} [transform.translation] (Mandatory for TRANSLATE) The translation (move values).
 * @apiParam {Number} transform.translation.x X-translation.
 * @apiParam {Number} transform.translation.y Y-translation.
 *
 * @apiParamExample {json} Request-Example:
 *     POST /a3eb8d/transformElement
 *     {
 *       "elementID": 3,
 *       "transform": {
 *         "transformationType": "ROTATE",
 *         "origin": {
 *           "x": 50,
 *           "y": 50
 *         },
 *         "rotationAngle": 45
 *       }
 *     }
 *
 * @apiUse ServerResponse
 *
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *     {
 *       "projectID": "a3eb8d",
 *       "canvas": {
 *         "width": 200,
 *         "height": 200,
 *         "allowTransformAttribute": true,
 *         "html": "<svg width="200.0" height="200.0" baseProfile="full" xmlns="http://www.w3.org/2000/svg"><polygon fill="#FFFFFF" fill-opacity="0.0" stroke="#000000" stroke-opacity="1.0" stroke-width="1.0" opacity="1.0" cx="50.0" cy="50.0" r="50.0" points="79.3892593383789,90.45085144042969 73.77641296386719,57.725425720214844 97.55282592773438,34.54914855957031 64.69463348388672,29.774574279785156 50.0,0.0 35.30537033081055,29.774574279785156 2.447174072265625,34.54914855957031 26.223587036132812,57.725425720214844 20.610736846923828,90.45085144042969 50.0,75.0" transform="translate(50.0 50.0) rotate(45.0) translate(-50.0 -50.0)"></polygon></svg>",
 *         "elements": [
 *           {
 *             "elementType": "LAYER",
 *             "id": 3,
 *             "visible": true,
 *             "shape": {
 *               "fillColour": {
 *                 "rgbColour": "#FFFFFF",
 *                 "opacity": 0
 *               },
 *               "strokeColour": {
 *                 "rgbColour": "#000000",
 *                 "opacity": 1
 *               },
 *               "strokeWidth": 1,
 *               "opacity": 1,
 *               "center": {
 *                 "x": 50,
 *                 "y": 50
 *               },
 *               "radius": 50,
 *               "edgeAmount": 5,
 *               "innerRadius": 25,
 *               "shapeType": "STAR",
 *               "transformations": [
 *                 {
 *                   "rotationAngle": 45,
 *                   "type": "ROTATION",
 *                   "origin": {
 *                     "x": 50,
 *                     "y": 50
 *                   }
 *                 }
 *               ],
 *             }
 *           }
 *         ],
 *       }
 *     }
 *
 * @apiUse ErrorResponse
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 500 Internal Server Error
 *     {
 *       "timestamp": "2019-01-13 14:18:33.171",
 *       "status": 500,
 *       "error": "Internal Server Error",
 *       "message": "Element with ID 2 does not exist",
 *       "path": "/a3eb8d/transformElement"
 *     }
 *
 */


/**
 * @api {post} /:projectID/removeElement Remove element
 * @apiName RemoveElement
 * @apiGroup ProjectElements
 * @apiDescription Removes the element with the given ID from the project.
 *
 * @apiParam {Number} projectID ID of the working project.
 * @apiParam {Number} elementID ID of the element to remove.

 * @apiParamExample {json} Request-Example:
 *     POST /cd04a4/removeElement
 *     {
 *       "elementID": 3
 *     }
 *
 * @apiUse ServerResponse
 *
 * @apiUse ErrorResponse
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 500 Internal Server Error
 *     {
 *       "timestamp": "2019-01-13 14:18:33.171",
 *       "status": 500,
 *       "error": "Internal Server Error",
 *       "message": "Element with ID 2 does not exist",
 *       "path": "/cd04a4/removeElement"
 *     }
 *
 */


/**
 * @api {get} /:projectID/download/:downloadFormat Download
 * @apiName Download
 * @apiGroup Download
 * @apiDescription Downloads the project canvas in SVG, PNG or JPG format.
 * URL needs to be opened in a browser to download.
 *
 * @apiParam {Number} projectID ID of the working project.
 * @apiParam {String="SVG","PNG","JPG"} downloadFormat Format to download the canvas in.

 * @apiParamExample {json} Request-Example:
 *     GET /cd04a4/download/JPG
 *
 */