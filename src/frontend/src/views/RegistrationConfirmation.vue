<template>
  <div class="container mt-5">
    <div class="row">
      <h3>Vos informations sont enregistrées !</h3>
    </div>
    <div class="row mt-2">
      <MemberDetails />
    </div>
  </div>
</template>
<script>
import MemberDetails from '@/components/MemberDetails'

export default {
  data() {
    return {
    }
  },
  components: {
    MemberDetails
  }
}
</script>
