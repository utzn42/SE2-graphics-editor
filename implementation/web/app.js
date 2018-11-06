// This is a dummy state to represent answers received from the server
var canvasStateServer = {
    canvasWidth: 200,
    canvasHeight: 200,
    activeLayer: -1,    // -1 is the canvas itself
    layers: [],
    html: "<svg width='200' height='200'></svg>"
};
var canvasStateClient;


// Runs upon loading the page
initializeClient();


function sendRequest(request) {
    canvasStateClient = serverStub(request);
    updateCanvas();
}


function updateCanvas() {
    document.getElementById("app_canvas").innerHTML = canvasStateClient.html;
    updateLayers();
    updateEdit(canvasStateClient.activeLayer);
}


function initializeClient() {
    sendRequest({
        type: "INIT",
        attributes: {}
    });
}


function updateLayers() {
    var html = "<ul>";
    for (var i = canvasStateClient.layers.length - 1; i >= 0; --i) {
        html += "<li><a href='javascript:updateEdit(" + i + ");'>Layer " + i + "</a></li>";
    }
    html += "<li><a href='javascript:updateEdit(-1);'>Canvas</a></li></ul>";
    document.getElementById("app_toolbar_layers").innerHTML = html;
}


function updateEdit(layer) {
    if (layer !== canvasStateClient.activeLayer) {
        switchActiveLayer(layer);
    }
    var html = "";
    if (layer === -1) {  // Show options for editing canvas
        html += "<label for='edit_width'>Canvas Width:</label>"
            + "<input type='text' name='edit_width' id='edit_width' value='" + canvasStateClient.canvasWidth + "' />";
        html += "<label for='edit_height'>Canvas Height:</label>"
            + "<input type='text' name='edit_height' id='edit_height' value='" + canvasStateClient.canvasHeight + "' />";
        html += "<button onclick='editLayer({layer: " + canvasStateClient.activeLayer + "," +
            "canvasWidth: document.getElementById(\"edit_width\").value," +
            "canvasHeight: document.getElementById(\"edit_height\").value" +
            "})'>Set</button>";
    }
    else {
        var attributes = getEditAttributes(canvasStateClient.layers[layer].shape);
        for (var attribute in attributes) {
            html += "<label for='edit_" + attribute + "'>" + attributes[attribute] + "</label>" +
                "<input type='text' name='edit_" + attribute + "' id='edit_" + attribute + "' value='" + canvasStateClient.layers[layer][attribute] + "' />";
        }
        html += "<button onclick='editLayer({layer: " + canvasStateClient.activeLayer + ",";
        for (attribute in attributes) {
            html += attribute + ": document.getElementById(\"edit_" + attribute + "\").value,";
        }
        html += "})'>Set</button>";
    }
    document.getElementById("app_toolbar_edit").innerHTML = html;
}


function getEditAttributes(shape) {
    switch(shape) {
        case "CIRCLE":
            return {
                cx: "Center x-Coordinate",
                cy: "Center y-Coordinate",
                r: "Radius",
                strokeColor: "Stroke Color",
                strokeWidth: "Stroke Width",
                fillColor: "Fill Color"
            };
        case "RECTANGLE":
            return {
                width: "Width",
                height: "Height",
                strokeColor: "Stroke Color",
                strokeWidth: "Stroke Width",
                fillColor: "Fill Color"
            };
        default:
            alert('Can\'t get Attributes for ' + shape);
    }
}


function addShape(shape) {
    sendRequest({
        type: "CREATE",
        attributes: {
            shape: shape
        }
    });
}


function editLayer(attributes) {
    sendRequest({
        type: "EDIT",
        attributes: attributes
    });
}


function switchActiveLayer(layer) {
    sendRequest({
        type: "SETACTIVE",
        attributes: {
            layer: layer
        }
    });
}


// DUMMY FUNCTION!
// Implement on Server
function serverStub(request) {
    switch (request.type) {

        case "INIT":
            break;

        case "CREATE":
            switch (request.attributes.shape) {
                case "CIRCLE":
                    var cx = canvasStateServer.canvasWidth/2;
                    var cy = canvasStateServer.canvasHeight/2;
                    var r = (((canvasStateServer.canvasWidth > canvasStateServer.canvasHeight) ? canvasStateServer.canvasHeight : canvasStateServer.canvasWidth)/3).toFixed(0);
                    var id = canvasStateServer.layers.length;
                    canvasStateServer.layers.push({
                        id: id,
                        shape: "CIRCLE",
                        cx: cx,
                        cy: cy,
                        r: r,
                        strokeColor: "black",
                        strokeWidth: "2",
                        fillColor: "transparent"
                    });
                    break;
                case "RECTANGLE":
                    var width = canvasStateServer.canvasWidth/2;
                    var height = canvasStateServer.canvasHeight/2;
                    var id = canvasStateServer.layers.length;
                    canvasStateServer.layers.push({
                        id: id,
                        shape: "RECTANGLE",
                        width: width,
                        height: height,
                        strokeColor: "black",
                        strokeWidth: "2",
                        fillColor: "transparent"
                    });
                    break;
                default:
                    alert('Server Returned Error 1');
            }
            break;

        case "EDIT":
            if (request.attributes.layer === -1) {
                canvasStateServer.canvasWidth = request.attributes.canvasWidth;
                canvasStateServer.canvasHeight = request.attributes.canvasHeight;
            }
            else {
                var layer = canvasStateServer.layers[request.attributes.layer];
                for (var attribute in request.attributes) {
                    if (layer.hasOwnProperty(attribute)) {
                        layer[attribute] = request.attributes[attribute];
                    }
                }
            }
            break;

        case "SETACTIVE":
            canvasStateServer.activeLayer = request.attributes.layer;
            break;

        default:
            alert('Server Returned Error 2');
    }
    serverGenerateHTML();
    return canvasStateServer;
}


// DUMMY FUNCTION!
// Implement on Server
function serverGenerateHTML() {
    var html = "<svg width='" + canvasStateServer.canvasWidth + "' height='" + canvasStateServer.canvasHeight + "'>";
    for (var layer = 0; layer < canvasStateServer.layers.length; ++layer) {
        html += serverGenerateLayerHTML(layer);
    }
    html += "</svg>";
    canvasStateServer.html = html;
}


// DUMMY FUNCTION!
// Implement on Server
function serverGenerateLayerHTML(layerId) {
    var layer = canvasStateServer.layers[layerId];
    var html = "";
    switch (layer.shape) {
        case "CIRCLE":
            html = "<circle id='layer_" + layer.id + "' " +
                "cx='" + layer.cx + "' " +
                "cy='" + layer.cy + "' " +
                "r='" + layer.r + "' " +
                "stroke='" + layer.strokeColor + "' " +
                "stroke-width='" + layer.strokeWidth +  "' " +
                "fill='" + layer.fillColor + "'>" +
                "</circle>";
            break;
        case "RECTANGLE":
            html = "<rect id='layer_" + layer.id + "' " +
                "width='" + layer.width + "' " +
                "height='" + layer.height + "' " +
                "stroke='" + layer.strokeColor + "' " +
                "stroke-width='" + layer.strokeWidth +  "' " +
                "fill='" + layer.fillColor + "'>" +
                "</rect>";
            break;
        default:
            alert('Server Returned Error');
    }
    return html;
}