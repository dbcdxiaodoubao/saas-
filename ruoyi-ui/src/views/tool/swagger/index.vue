<template>
  <i-frame :src="src" />
</template>
<script>
import iFrame from "@/components/iFrame/index"
export default {
  name: "Swagger",
  components: { iFrame },
  data() {
    return {
      src: process.env.VUE_APP_BASE_API + "/doc.html"
    }
  }
}
</script>
