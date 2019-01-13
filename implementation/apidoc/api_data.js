define({ "api": [
  {
    "type": "get",
    "url": "/:projectID/download/:downloadFormat",
    "title": "Download",
    "name": "Download",
    "group": "Download",
    "description": "<p>Downloads the project canvas in SVG, PNG or JPG format. URL needs to be opened in a browser to download.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "projectID",
            "description": "<p>ID of the working project.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"SVG\"",
              "\"PNG\"",
              "\"JPG\""
            ],
            "optional": false,
            "field": "downloadFormat",
            "description": "<p>Format to download the canvas in.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "GET /cd04a4/download/JPG",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./serverapi.js",
    "groupTitle": "Download"
  },
  {
    "type": "get",
    "url": "/create",
    "title": "Create project",
    "name": "CreateProject",
    "group": "Project",
    "description": "<p>Creates a new project and returns it.</p>",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"projectID\": \"cd2662\",\n  \"canvas\": {\n    \"width\": 200,\n    \"height\": 200,\n    \"allowTransformAttribute\": true,\n    \"html\": \"<svg width=\"200.0\" height=\"200.0\" baseProfile=\"full\" xmlns=\"http://www.w3.org/2000/svg\"></svg>\",\n    \"elements\": [],\n  }\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "projectID",
            "description": "<p>ID of the project.</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "canvas",
            "description": "<p>Project canvas including all SVG elements.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "canvas.width",
            "description": "<p>Width of project canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "canvas.height",
            "description": "<p>Height of project canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "canvas.allowTransformAttribute",
            "description": "<p>If false, disallows HTML &quot;transform&quot; attributes for the entire canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "canvas.html",
            "description": "<p>The canvas as HTML SVG object.</p>"
          },
          {
            "group": "Success 200",
            "type": "Object[]",
            "optional": false,
            "field": "canvas.elements",
            "description": "<p>The list of elements on the canvas.</p>"
          }
        ]
      }
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 405 Method Not Allowed\n{\n  \"timestamp\": \"2019-01-13 10:47:51.98\",\n  \"status\": 405,\n  \"error\": \"Method Not Allowed\",\n  \"message\": \"Request method 'POST' not supported\",\n  \"path\": \"/create\"\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "timestamp",
            "description": "<p>Time the error occurred.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "Number",
            "optional": false,
            "field": "status",
            "description": "<p>HTML error code.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>HTML error name.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>Error message.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>URL of the request that caused the error.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./serverapi.js",
    "groupTitle": "Project"
  },
  {
    "type": "post",
    "url": "/:projectID/editProject",
    "title": "Edit project",
    "name": "EditProject",
    "group": "Project",
    "description": "<p>Allows modification of project properties.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "projectID",
            "description": "<p>(URL param) ID of the working project.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "width",
            "description": "<p>New canvas width.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "height",
            "description": "<p>New canvas height.</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "allowTransformAttribute",
            "description": "<p>If false, disallows HTML &quot;transform&quot; attributes for the entire canvas.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "POST /8005f0/editProject\n{\n  \"width\": 800,\n  \"allowTransformAttribute\": false\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"projectID\": \"8005f0\",\n  \"canvas\": {\n    \"width\": 800,\n    \"height\": 200,\n    \"allowTransformAttribute\": false,\n    \"html\": \"<svg width=\"800.0\" height=\"200.0\" baseProfile=\"full\" xmlns=\"http://www.w3.org/2000/svg\"></svg>\",\n    \"elements\": [],\n  }\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "projectID",
            "description": "<p>ID of the project.</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "canvas",
            "description": "<p>Project canvas including all SVG elements.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "canvas.width",
            "description": "<p>Width of project canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "canvas.height",
            "description": "<p>Height of project canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "canvas.allowTransformAttribute",
            "description": "<p>If false, disallows HTML &quot;transform&quot; attributes for the entire canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "canvas.html",
            "description": "<p>The canvas as HTML SVG object.</p>"
          },
          {
            "group": "Success 200",
            "type": "Object[]",
            "optional": false,
            "field": "canvas.elements",
            "description": "<p>The list of elements on the canvas.</p>"
          }
        ]
      }
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 500 Internal Server Error\n{\n  \"timestamp\": \"2019-01-13 13:01:46.189\",\n  \"status\": 500,\n  \"error\": \"Internal Server Error\",\n  \"message\": \"Canvas width and height must both be positive!\",\n  \"path\": \"/8005f0/editProject\"\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "timestamp",
            "description": "<p>Time the error occurred.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "Number",
            "optional": false,
            "field": "status",
            "description": "<p>HTML error code.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>HTML error name.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>Error message.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>URL of the request that caused the error.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./serverapi.js",
    "groupTitle": "Project"
  },
  {
    "type": "post",
    "url": "/:projectID/addLayerGroup",
    "title": "Add layer group",
    "name": "AddLayerGroup",
    "group": "ProjectElements",
    "description": "<p>Creates a layer group that can hold any number of elements. The position at which to create the layer group can be specified with the parameters. The placeIntoElementID parameter will always override placeBeforeElementID if both are given. If none are given, will insert the layer group at the very last position.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "projectID",
            "description": "<p>ID of the working project.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "placeBeforeElementID",
            "description": "<p>The ID of the element to place the new layer group before.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "placeIntoElementID",
            "description": "<p>The ID of the element to place the new layer group in. Will cause errors if that element is not a layer group itself.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "POST /cd04a4/addLayerGroup\n{\n  \"placeIntoElementID\": 0\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"projectID\": \"cd04a4\",\n  \"canvas\": {\n    \"width\": 200,\n    \"height\": 200,\n    \"allowTransformAttribute\": true,\n    \"html\": \"<svg width=\"200.0\" height=\"200.0\" baseProfile=\"full\" xmlns=\"http://www.w3.org/2000/svg\"></svg>\",\n    \"elements\": [\n      {\n        \"elementType\": \"LAYER_GROUP\",\n        \"id\": 0,\n        \"visible\": true,\n        \"elements\": [\n          {\n            \"elementType\": \"LAYER_GROUP\",\n            \"id\": 2,\n            \"visible\": true,\n            \"elements\": [],\n          }\n        ],\n      },\n      {\n        \"elementType\": \"LAYER_GROUP\",\n        \"id\": 1,\n        \"visible\": true,\n        \"elements\": [],\n      }\n    ],\n  }\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "projectID",
            "description": "<p>ID of the project.</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "canvas",
            "description": "<p>Project canvas including all SVG elements.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "canvas.width",
            "description": "<p>Width of project canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "canvas.height",
            "description": "<p>Height of project canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "canvas.allowTransformAttribute",
            "description": "<p>If false, disallows HTML &quot;transform&quot; attributes for the entire canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "canvas.html",
            "description": "<p>The canvas as HTML SVG object.</p>"
          },
          {
            "group": "Success 200",
            "type": "Object[]",
            "optional": false,
            "field": "canvas.elements",
            "description": "<p>The list of elements on the canvas.</p>"
          }
        ]
      }
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 500 Internal Server Error\n{\n  \"timestamp\": \"2019-01-13 12:37:38.555\",\n  \"status\": 500,\n  \"error\": \"Internal Server Error\",\n  \"message\": \"Cannot add into element 0; Not a layer group!\",\n  \"path\": \"/af5570/addLayerGroup\"\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "timestamp",
            "description": "<p>Time the error occurred.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "Number",
            "optional": false,
            "field": "status",
            "description": "<p>HTML error code.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>HTML error name.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>Error message.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>URL of the request that caused the error.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./serverapi.js",
    "groupTitle": "ProjectElements"
  },
  {
    "type": "post",
    "url": "/:projectID/addShape",
    "title": "Add shape",
    "name": "AddShape",
    "group": "ProjectElements",
    "description": "<p>Creates a layer with the given shape type. The position at which to create the layer can be specified with the parameters. The placeIntoElementID parameter will always override placeBeforeElementID if both are given. If none are given, will insert the layer at the very last position.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "projectID",
            "description": "<p>ID of the working project.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"CIRCLE\"",
              "\"ELLIPSE\"",
              "\"LINE\"",
              "\"POLYGON\"",
              "\"REGULAR_POLYGON\"",
              "\"STAR\"",
              "\"TEXT\""
            ],
            "optional": false,
            "field": "shapeType",
            "description": "<p>The type of shape to add.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "placeBeforeElementID",
            "description": "<p>The ID of the element to place the new layer before.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "placeIntoElementID",
            "description": "<p>The ID of the element to place the new layer in. Will cause errors if that element is not a layer group.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "POST /cd04a4/addShape\n{\n  \"shapeType\": \"CIRCLE\",\n  \"placeIntoElementID\": 0\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"projectID\": \"cd04a4\",\n  \"canvas\": {\n    \"width\": 200,\n    \"height\": 200,\n    \"allowTransformAttribute\": true,\n    \"html\": \"<svg width=\"200.0\" height=\"200.0\" baseProfile=\"full\" xmlns=\"http://www.w3.org/2000/svg\"></svg>\",\n    \"elements\": [\n      {\n        \"elementType\": \"GROUP_LAYER\",\n        \"id\": 0,\n        \"visible\": true,\n        \"elements\": [\n           {\n             \"elementType\": \"LAYER\",\n             \"id\": 2,\n             \"visible\": true,\n             \"shape\": {\n               \"fillColour\": {\n                 \"rgbColour\": \"#FFFFFF\",\n                 \"opacity\": 0\n               },\n               \"strokeColour\": {\n                 \"rgbColour\": \"#000000\",\n                 \"opacity\": 1\n               },\n               \"strokeWidth\": 1,\n               \"opacity\": 1,\n               \"center\": {\n                 \"x\": 50,\n                 \"y\": 50\n               },\n               \"radius\": 50,\n               \"shapeType\": \"CIRCLE\",\n               \"transformations\": [],\n             }\n           }\n        ],\n      },\n      {\n        \"elementType\": \"LAYER_GROUP\",\n        \"id\": 1,\n        \"visible\": true,\n        \"elements\": [],\n      }\n    ],\n  }\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "projectID",
            "description": "<p>ID of the project.</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "canvas",
            "description": "<p>Project canvas including all SVG elements.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "canvas.width",
            "description": "<p>Width of project canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "canvas.height",
            "description": "<p>Height of project canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "canvas.allowTransformAttribute",
            "description": "<p>If false, disallows HTML &quot;transform&quot; attributes for the entire canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "canvas.html",
            "description": "<p>The canvas as HTML SVG object.</p>"
          },
          {
            "group": "Success 200",
            "type": "Object[]",
            "optional": false,
            "field": "canvas.elements",
            "description": "<p>The list of elements on the canvas.</p>"
          }
        ]
      }
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 500 Internal Server Error\n{\n  \"timestamp\": \"2019-01-13 12:37:38.555\",\n  \"status\": 500,\n  \"error\": \"Internal Server Error\",\n  \"message\": \"Cannot add into element 0; Not a layer group!\",\n  \"path\": \"/af5570/addShape\"\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "timestamp",
            "description": "<p>Time the error occurred.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "Number",
            "optional": false,
            "field": "status",
            "description": "<p>HTML error code.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>HTML error name.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>Error message.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>URL of the request that caused the error.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./serverapi.js",
    "groupTitle": "ProjectElements"
  },
  {
    "type": "post",
    "url": "/:projectID/editElement",
    "title": "Edit element",
    "name": "EditElement",
    "group": "ProjectElements",
    "description": "<p>Allows setting the visibility of an element.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "projectID",
            "description": "<p>ID of the working project.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "elementID",
            "description": "<p>ID of the element to modify.</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": false,
            "field": "visible",
            "description": "<p>True to make element visible, false otherwise.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "POST /a3eb8d/editElement\n{\n  \"elementID\": 0,\n  \"visible\": false\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"projectID\": \"a3eb8d\",\n  \"canvas\": {\n    \"width\": 200,\n    \"height\": 200,\n    \"allowTransformAttribute\": true,\n    \"html\": \"<svg width=\"200.0\" height=\"200.0\" baseProfile=\"full\" xmlns=\"http://www.w3.org/2000/svg\"></svg>\",\n    \"elements\": [\n      {\n        \"elementType\": \"LAYER\",\n        \"id\": 0,\n        \"visible\": false,\n        \"shape\": {\n          \"fillColour\": {\n            \"rgbColour\": \"#FFFFFF\",\n            \"opacity\": 0\n          },\n          \"strokeColour\": {\n            \"rgbColour\": \"#000000\",\n            \"opacity\": 1\n          },\n          \"strokeWidth\": 1,\n          \"opacity\": 1,\n          \"coordinates\": [\n            {\n              \"x\": 0,\n              \"y\": 0\n            },\n            {\n              \"x\": 100,\n              \"y\": 100\n            }\n          ],\n          \"shapeType\": \"LINE\",\n          \"transformations\": [],\n        }\n      }\n    ],\n  }\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "projectID",
            "description": "<p>ID of the project.</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "canvas",
            "description": "<p>Project canvas including all SVG elements.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "canvas.width",
            "description": "<p>Width of project canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "canvas.height",
            "description": "<p>Height of project canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "canvas.allowTransformAttribute",
            "description": "<p>If false, disallows HTML &quot;transform&quot; attributes for the entire canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "canvas.html",
            "description": "<p>The canvas as HTML SVG object.</p>"
          },
          {
            "group": "Success 200",
            "type": "Object[]",
            "optional": false,
            "field": "canvas.elements",
            "description": "<p>The list of elements on the canvas.</p>"
          }
        ]
      }
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 405 Method Not Allowed\n{\n  \"timestamp\": \"2019-01-13T12:21:58.022+0000\",\n  \"status\": 405,\n  \"error\": \"Method Not Allowed\",\n  \"message\": \"Request method 'GET' not supported\",\n  \"path\": \"/a3eb8d/editElement\"\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "timestamp",
            "description": "<p>Time the error occurred.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "Number",
            "optional": false,
            "field": "status",
            "description": "<p>HTML error code.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>HTML error name.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>Error message.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>URL of the request that caused the error.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./serverapi.js",
    "groupTitle": "ProjectElements"
  },
  {
    "type": "post",
    "url": "/:projectID/modifyShape",
    "title": "Modify shape",
    "name": "ModifyShape",
    "group": "ProjectElements",
    "description": "<p>Modifies a shape using the given attributes. Possibly attributes can vary depending on the shapeType and all attributes are optional. The structure of the attributes to modify has to match the structure of a shape returned by the API.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "projectID",
            "description": "<p>ID of the working project.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "elementID",
            "description": "<p>ID of the element to modify.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"CIRCLE\"",
              "\"ELLIPSE\"",
              "\"LINE\"",
              "\"POLYGON\"",
              "\"REGULAR_POLYGON\"",
              "\"STAR\"",
              "\"TEXT\""
            ],
            "optional": false,
            "field": "shapeType",
            "description": "<p>The type of shape to modify.</p>"
          },
          {
            "group": "Parameter",
            "type": "Object",
            "optional": true,
            "field": "fillColour",
            "description": "<p>(For all shapes) The fill colour of the shape.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "fillColour.rgbColour",
            "description": "<p>Hex value. Takes precedence over individual colour values.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "0-255",
            "optional": true,
            "field": "fillColour.red",
            "description": "<p>Red value.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "0-255",
            "optional": true,
            "field": "fillColour.green",
            "description": "<p>Green value.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "0-255",
            "optional": true,
            "field": "fillColour.blue",
            "description": "<p>Blue value.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "0-1",
            "optional": true,
            "field": "fillColour.opacity",
            "description": "<p>Opacity.</p>"
          },
          {
            "group": "Parameter",
            "type": "Object",
            "optional": true,
            "field": "strokeColour",
            "description": "<p>(For all shapes) The stroke colour of the shape.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "strokeColour.rgbColour",
            "description": "<p>Hex value. Takes precedence over individual colour values.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "0-255",
            "optional": true,
            "field": "strokeColour.red",
            "description": "<p>Red value.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "0-255",
            "optional": true,
            "field": "strokeColour.green",
            "description": "<p>Green value.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "0-255",
            "optional": true,
            "field": "strokeColour.blue",
            "description": "<p>Blue value.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "0-1",
            "optional": true,
            "field": "strokeColour.opacity",
            "description": "<p>Opacity.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "strokeWidth",
            "description": "<p>(For all shapes) The stroke width of the shape.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "size": "0-1",
            "optional": true,
            "field": "opacity",
            "description": "<p>(For all shapes) The opacity of the shape.</p>"
          },
          {
            "group": "Parameter",
            "type": "Object",
            "optional": true,
            "field": "center",
            "description": "<p>(For CIRCLE, ELLIPSE, REGULAR_POLYGON, STAR, TEXT) The center of the shape.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "center.x",
            "description": "<p>X-coordinate</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "center.y",
            "description": "<p>Y-coordinate</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "radius",
            "description": "<p>(For CIRCLE, REGULAR_POLYGON, STAR) The (outer) radius of the shape.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "radiusX",
            "description": "<p>(For ELLIPSE) The x-radius of the shape.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "radiusY",
            "description": "<p>(For ELLIPSE) The y-radius of the shape.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "edgeAmount",
            "description": "<p>(For REGULAR_POLYGON, STAR) The number of edges of the shape (or half the number of edges in case of a STAR).</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "innerRadius",
            "description": "<p>(For STAR) The inner radius of the shape.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "displayText",
            "description": "<p>(For TEXT) The displayed text of the shape.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "font",
            "description": "<p>(For TEXT) The font the shape uses for its display text.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "fontSize",
            "description": "<p>(For TEXT) The font size the shape uses for its display text.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "POST /a3eb8d/modifyShape\n{\n  \"elementID\": 1,\n  \"shapeType\": \"TEXT\",\n  \"fillColour\": {\n    \"hex\": \"#33AAFF\",\n    \"opacity\": 0.5\n  },\n  \"center\": {\n    \"x\": 110,\n    \"y\": 40.7\n  },\n  \"displayText\": \"ModifiedText\",\n  \"font\": \"Times New Roman\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"projectID\": \"a3eb8d\",\n  \"canvas\": {\n    \"width\": 200,\n    \"height\": 200,\n    \"allowTransformAttribute\": true,\n    \"html\": \"<svg width=\"200.0\" height=\"200.0\" baseProfile=\"full\" xmlns=\"http://www.w3.org/2000/svg\"><text fill=\"#33AAFF\" fill-opacity=\"0.5\" stroke=\"#000000\" stroke-opacity=\"1.0\" stroke-width=\"0.0\" opacity=\"1.0\" x=\"110.0\" y=\"40.7\" font-family=\"Times New Roman\" font-size=\"12.0\" text-anchor=\"middle\" alignment-baseline=\"middle\">ModifiedText</text></svg>\",\n    \"elements\": [\n      {\n        \"elementType\": \"LAYER\",\n        \"id\": 1,\n        \"visible\": true,\n        \"shape\": {\n          \"fillColour\": {\n            \"rgbColour\": \"#33AAFF\",\n            \"opacity\": 0.5\n          },\n          \"strokeColour\": {\n            \"rgbColour\": \"#000000\",\n            \"opacity\": 1\n          },\n          \"strokeWidth\": 0,\n          \"opacity\": 1,\n          \"center\": {\n            \"x\": 110,\n            \"y\": 40.7\n          },\n          \"displayText\": \"ModifiedText\",\n          \"font\": \"Times New Roman\",\n          \"fontSize\": 12,\n          \"shapeType\": \"TEXT\",\n          \"transformations\": [],\n        }\n      }\n    ],\n  }\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "projectID",
            "description": "<p>ID of the project.</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "canvas",
            "description": "<p>Project canvas including all SVG elements.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "canvas.width",
            "description": "<p>Width of project canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "canvas.height",
            "description": "<p>Height of project canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "canvas.allowTransformAttribute",
            "description": "<p>If false, disallows HTML &quot;transform&quot; attributes for the entire canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "canvas.html",
            "description": "<p>The canvas as HTML SVG object.</p>"
          },
          {
            "group": "Success 200",
            "type": "Object[]",
            "optional": false,
            "field": "canvas.elements",
            "description": "<p>The list of elements on the canvas.</p>"
          }
        ]
      }
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 500 Internal Server Error\n{\n  \"timestamp\": \"2019-01-13 13:50:23.866\",\n  \"status\": 500,\n  \"error\": \"Internal Server Error\",\n  \"message\": \"Element with ID 0 is not a single shape!\",\n  \"path\": \"/a3eb8d/modifyShape\"\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "timestamp",
            "description": "<p>Time the error occurred.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "Number",
            "optional": false,
            "field": "status",
            "description": "<p>HTML error code.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>HTML error name.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>Error message.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>URL of the request that caused the error.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./serverapi.js",
    "groupTitle": "ProjectElements"
  },
  {
    "type": "post",
    "url": "/:projectID/removeElement",
    "title": "Remove element",
    "name": "RemoveElement",
    "group": "ProjectElements",
    "description": "<p>Removes the element with the given ID from the project.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "projectID",
            "description": "<p>ID of the working project.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "elementID",
            "description": "<p>ID of the element to remove.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "POST /cd04a4/removeElement\n{\n  \"elementID\": 3\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 500 Internal Server Error\n{\n  \"timestamp\": \"2019-01-13 14:18:33.171\",\n  \"status\": 500,\n  \"error\": \"Internal Server Error\",\n  \"message\": \"Element with ID 2 does not exist\",\n  \"path\": \"/cd04a4/removeElement\"\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "timestamp",
            "description": "<p>Time the error occurred.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "Number",
            "optional": false,
            "field": "status",
            "description": "<p>HTML error code.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>HTML error name.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>Error message.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>URL of the request that caused the error.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./serverapi.js",
    "groupTitle": "ProjectElements",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "projectID",
            "description": "<p>ID of the project.</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "canvas",
            "description": "<p>Project canvas including all SVG elements.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "canvas.width",
            "description": "<p>Width of project canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "canvas.height",
            "description": "<p>Height of project canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "canvas.allowTransformAttribute",
            "description": "<p>If false, disallows HTML &quot;transform&quot; attributes for the entire canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "canvas.html",
            "description": "<p>The canvas as HTML SVG object.</p>"
          },
          {
            "group": "Success 200",
            "type": "Object[]",
            "optional": false,
            "field": "canvas.elements",
            "description": "<p>The list of elements on the canvas.</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "/:projectID/transformElement",
    "title": "Transform element",
    "name": "TransformElement",
    "group": "ProjectElements",
    "description": "<p>Transforms an element using the given attributes. Some transformations on some shapes may cause errors if allowTransformAttribute of the Project is set to false. Transformations on layer groups distribute to the underlying layers.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "projectID",
            "description": "<p>ID of the working project.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "elementID",
            "description": "<p>ID of the element to modify.</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": true,
            "field": "clearAll",
            "description": "<p>If true, clears all transformations affecting the element.</p>"
          },
          {
            "group": "Parameter",
            "type": "Object",
            "optional": true,
            "field": "transform",
            "description": "<p>The transformation to apply to the element.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"ROTATE\"",
              "\"SCALE\"",
              "\"SKEW\"",
              "\"TRANSLATE\"",
              "\"UNIFORM_SCALE\""
            ],
            "optional": true,
            "field": "transform.transformationType",
            "description": "<p>The type of transformation</p>"
          },
          {
            "group": "Parameter",
            "type": "Object",
            "optional": true,
            "field": "transform.origin",
            "description": "<p>The origin to perform the transformation in respect to.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "transform.origin.x",
            "description": "<p>X-coordinate.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "transform.origin.y",
            "description": "<p>Y-coordinate.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "transform.rotationAngle",
            "description": "<p>(Mandatory for ROTATION) The rotation angle.</p>"
          },
          {
            "group": "Parameter",
            "type": "Object",
            "optional": true,
            "field": "transform.scale",
            "description": "<p>(Mandatory for SCALE and UNIFORM_SCALE) The scale factors. Has to be a single Number for UNIFORM_SCALE.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "transform.scale.x",
            "description": "<p>(SCALE) X scale factor.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "transform.scale.y",
            "description": "<p>(SCALE) Y scale factor.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "\"x\"",
              "\"y\""
            ],
            "optional": true,
            "field": "transform.skewAxis",
            "description": "<p>(Mandatory for SKEW) The skew axis.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "transform.skewAngle",
            "description": "<p>(Mandatory for SKEW) The skew angle. Perfect right angles will cause errors.</p>"
          },
          {
            "group": "Parameter",
            "type": "Object",
            "optional": true,
            "field": "transform.translation",
            "description": "<p>(Mandatory for TRANSLATE) The translation (move values).</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "transform.translation.x",
            "description": "<p>X-translation.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "transform.translation.y",
            "description": "<p>Y-translation.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "POST /a3eb8d/transformElement\n{\n  \"elementID\": 3,\n  \"transform\": {\n    \"transformationType\": \"ROTATE\",\n    \"origin\": {\n      \"x\": 50,\n      \"y\": 50\n    },\n    \"rotationAngle\": 45\n  }\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"projectID\": \"a3eb8d\",\n  \"canvas\": {\n    \"width\": 200,\n    \"height\": 200,\n    \"allowTransformAttribute\": true,\n    \"html\": \"<svg width=\"200.0\" height=\"200.0\" baseProfile=\"full\" xmlns=\"http://www.w3.org/2000/svg\"><polygon fill=\"#FFFFFF\" fill-opacity=\"0.0\" stroke=\"#000000\" stroke-opacity=\"1.0\" stroke-width=\"1.0\" opacity=\"1.0\" cx=\"50.0\" cy=\"50.0\" r=\"50.0\" points=\"79.3892593383789,90.45085144042969 73.77641296386719,57.725425720214844 97.55282592773438,34.54914855957031 64.69463348388672,29.774574279785156 50.0,0.0 35.30537033081055,29.774574279785156 2.447174072265625,34.54914855957031 26.223587036132812,57.725425720214844 20.610736846923828,90.45085144042969 50.0,75.0\" transform=\"translate(50.0 50.0) rotate(45.0) translate(-50.0 -50.0)\"></polygon></svg>\",\n    \"elements\": [\n      {\n        \"elementType\": \"LAYER\",\n        \"id\": 3,\n        \"visible\": true,\n        \"shape\": {\n          \"fillColour\": {\n            \"rgbColour\": \"#FFFFFF\",\n            \"opacity\": 0\n          },\n          \"strokeColour\": {\n            \"rgbColour\": \"#000000\",\n            \"opacity\": 1\n          },\n          \"strokeWidth\": 1,\n          \"opacity\": 1,\n          \"center\": {\n            \"x\": 50,\n            \"y\": 50\n          },\n          \"radius\": 50,\n          \"edgeAmount\": 5,\n          \"innerRadius\": 25,\n          \"shapeType\": \"STAR\",\n          \"transformations\": [\n            {\n              \"rotationAngle\": 45,\n              \"type\": \"ROTATION\",\n              \"origin\": {\n                \"x\": 50,\n                \"y\": 50\n              }\n            }\n          ],\n        }\n      }\n    ],\n  }\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "projectID",
            "description": "<p>ID of the project.</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "canvas",
            "description": "<p>Project canvas including all SVG elements.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "canvas.width",
            "description": "<p>Width of project canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "canvas.height",
            "description": "<p>Height of project canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "canvas.allowTransformAttribute",
            "description": "<p>If false, disallows HTML &quot;transform&quot; attributes for the entire canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "canvas.html",
            "description": "<p>The canvas as HTML SVG object.</p>"
          },
          {
            "group": "Success 200",
            "type": "Object[]",
            "optional": false,
            "field": "canvas.elements",
            "description": "<p>The list of elements on the canvas.</p>"
          }
        ]
      }
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 500 Internal Server Error\n{\n  \"timestamp\": \"2019-01-13 14:18:33.171\",\n  \"status\": 500,\n  \"error\": \"Internal Server Error\",\n  \"message\": \"Element with ID 2 does not exist\",\n  \"path\": \"/a3eb8d/transformElement\"\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "timestamp",
            "description": "<p>Time the error occurred.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "Number",
            "optional": false,
            "field": "status",
            "description": "<p>HTML error code.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>HTML error name.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>Error message.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>URL of the request that caused the error.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./serverapi.js",
    "groupTitle": "ProjectElements"
  },
  {
    "type": "get",
    "url": "/:projectID",
    "title": "Load project",
    "name": "LoadProject",
    "group": "Project",
    "description": "<p>Returns the project with the specified ID if it exists.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "projectID",
            "description": "<p>ID of the project to load.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "GET /cd2662",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"projectID\": \"cd2662\",\n  \"canvas\": {\n    \"width\": 200,\n    \"height\": 200,\n    \"allowTransformAttribute\": true,\n    \"html\": \"<svg width=\"200.0\" height=\"200.0\" baseProfile=\"full\" xmlns=\"http://www.w3.org/2000/svg\"></svg>\",\n    \"elements\": [],\n  }\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "projectID",
            "description": "<p>ID of the project.</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "canvas",
            "description": "<p>Project canvas including all SVG elements.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "canvas.width",
            "description": "<p>Width of project canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "canvas.height",
            "description": "<p>Height of project canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "canvas.allowTransformAttribute",
            "description": "<p>If false, disallows HTML &quot;transform&quot; attributes for the entire canvas.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "canvas.html",
            "description": "<p>The canvas as HTML SVG object.</p>"
          },
          {
            "group": "Success 200",
            "type": "Object[]",
            "optional": false,
            "field": "canvas.elements",
            "description": "<p>The list of elements on the canvas.</p>"
          }
        ]
      }
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 500 Internal Server Error\n{\n  \"timestamp\": \"2019-01-13 11:56:49.65\",\n  \"status\": 500,\n  \"error\": \"Internal Server Error\",\n  \"message\": \"Project ID abcdef does not exist!\",\n  \"path\": \"/abcdef\"\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "timestamp",
            "description": "<p>Time the error occurred.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "Number",
            "optional": false,
            "field": "status",
            "description": "<p>HTML error code.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "error",
            "description": "<p>HTML error name.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>Error message.</p>"
          },
          {
            "group": "Error 4xx",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>URL of the request that caused the error.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./serverapi.js",
    "groupTitle": "Project"
  }
] });
