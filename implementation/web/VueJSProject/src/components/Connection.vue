<template>
  <div class="connection">
    <h1>Project {{projectID}}</h1>
  </div>
</template>

<script>

  import {dataBus} from "../main";

  /**
   * This component deals with sending requests to and receiving responses from the REST API.
   * Upon creation, it starts listening to events transmitted over the {@link dataBus}.
   * After sending a GET or POST request, it sends the response over the dataBus via a "response" event.
   * Note that any event that sends a POST request needs to include the request body.
   *
   * Currently, it listens to the following events on the dataBus:
   *   * "create":
   *       => Sends a GET request to "<baseURL>/create".
   *       This should always be done first, since it is needed to set up the projectID.
   *       Required data: None
   *   * "editCanvas":
   *       => Sends a POST request to "<baseURL>/<projectID>/editCanvas".
   *       Required data: (object) requestBody
   *   * "addLayer":
   *       => Sends a POST request to "<baseURL>/<projectID>/addLayer".
   *       Required data: (object) requestBody
   *   * "editLayer":
   *       => Sends a POST request to "<baseURL>/<projectID>/editLayer".
   *       Required data: (object) requestBody
   *   * "deleteLayer":
   *       => Sends a POST request to "<baseURL>/<projectID>/deleteLayer".
   *       Required data: (object) requestBody
   *   * "addShape":
   *       => Sends a POST request to "<baseURL>/<projectID>/addShape".
   *       Required data: (object) requestBody
   *   * "editShape":
   *       => Sends a POST request to "<baseURL>/<projectID>/editShape".
   *       Required data: (object) requestBody
   *   * "transformShape":
   *       => Sends a POST request to "<baseURL>/<projectID>/transformShape".
   *       Required data: (object) requestBody
   *   * "deleteShape":
   *       => Sends a POST request to "<baseURL>/<projectID>/deleteShape".
   *       Required data: (object) requestBody
   *   * "download":
   *       => Opens "<baseURL>/<projectID>/download/<fileType>" in a new window.
   *       This will not send a "response" event over the eventBus.
   *       Required data: (String) fileType
   *
   * props: baseUrl - The baseURL of the REST API
   */
  export default {

    name: "Connection",

    props: {
      baseUrl: String
    },

    data() {
      return {
        projectID: ""
      }
    },

    methods: {

      createCanvas: function() {
        console.log("GET REQUEST TO URL=\"" + this.baseUrl + "/create\":");
        this.$http.get(this.baseUrl + "/create")
          .then(response => {
            console.log("DATA RECEIVED:");
            console.log(response);
            this.projectID = response.body.projectID;
            dataBus.$emit('response', response);
          })
      },

      postRequest: function(requestType, data) {
        console.log("POST REQUEST TO URL=\"" + this.baseUrl + "/" + requestType + "/" + this.projectID + "\":");
        console.log("SENDING DATA:");
        console.log(data);
        this.$http.post(this.baseUrl + "/" + requestType + "/" + this.projectID, data)
          .then(response => {
            console.log("DATA RECEIVED:");
            console.log(response);
            dataBus.$emit('response', response);
          })
      },

      downloadRequest: function(fileType) {
        console.log("OPENING URL=\"" + this.baseUrl + "/download/" + this.projectID + "\":");
        window.open(this.baseUrl + "/download/" + this.projectID + "/" + fileType);
      }

    },

    created: function () {
      dataBus.$on('create', () => this.createCanvas());
      dataBus.$on('editCanvas', (data) => this.postRequest('editCanvas', data));
      dataBus.$on('addLayer', (data) => this.postRequest('addLayer', data));
      dataBus.$on('editLayer', (data) => this.postRequest('editLayer', data));
      dataBus.$on('deleteLayer', (data) => this.postRequest('deleteLayer', data));
      dataBus.$on('addShape', (data) => this.postRequest('addShape', data));
      dataBus.$on('editShape', (data) => this.postRequest('editShape', data));
      dataBus.$on('transformShape', (data) => this.postRequest('transformShape', data));
      dataBus.$on('deleteShape', (data) => this.postRequest('deleteShape', data));
      dataBus.$on('download', (fileType) => this.downloadRequest(fileType));
    }

  }

</script>

<style scoped>

  .connection {
    display: none;
  }

</style>
