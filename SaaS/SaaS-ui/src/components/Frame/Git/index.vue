<template>
  <div>
    <svg-icon icon-class="github" @click="goto"/>
  </div>
</template>

<script>
export default {
  name: 'Git',
  data() {
    return {
      url: 'https://gitee.com/xxxxx'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
