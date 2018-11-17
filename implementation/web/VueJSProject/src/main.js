// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import vueResource from 'vue-resource'
import App from './App'

Vue.use(vueResource);

Vue.config.productionTip = false;

/**
 * A bus for sending and listening to events between components
 * @type {Vue | CombinedVueInstance<V, object, object, object, Record<never, any>>}
 */
export const dataBus = new Vue();

/* eslint-disable no-new */
new Vue({
  el: '#app',
  components: {App},
  template: '<App/>'
});
