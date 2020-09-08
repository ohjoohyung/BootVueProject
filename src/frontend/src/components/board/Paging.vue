<template>
  <div>
    <button v-on:click="goPage(1)">&lt;&lt;</button>
    <button v-on:click="goPage(myPaging.prevPage)">&lt;</button>
    <span v-for="index in myPaging.pageNumberList"
          v-bind:key="index"
    >
      <span v-on:click="goPage(index)"
            v-if="index === myPaging.pageNumber"
            v-text="index"
            style="font-weight: bold"
      >
      </span>
      <span v-on:click="goPage(index)"
            v-else
            v-text="index"
      >
      </span>
    </span>
    <button v-on:click="goPage(myPaging.nextPage)">&gt;</button>
    <button v-on:click="goPage(myPaging.endPage)">&gt;&gt;</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      myPaging: {
        pageNumber: 1, //현재 페이지 번호
        pageSize: 10, //페이지 사이즈
        totalPages: 0, //전체 페이지 개수
        totalElements: 0, //전체 게시물 개수
        firstPage: 1, //첫 페이지
        prevPage: 0, //이전 페이지(이전 페이지 블록)
        nextPage: 0, //다음 페이지(다음 페이지 블록)
        endPage: 0, //끝 페이지
        pageNumberList: [1] //페이지 번호 리스트
      }
    }
  },
  created() {
    this.myPaging = this.$store.getters.getPaging
  },
  methods: {
    //이벤트 버스
    goPage(pageNo) {
      this.$EventBus.$emit('goPage', (pageNo - 1))
    }
  }
}
</script>

<style scoped>

</style>