/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const mydatasRouter = {
  path: '/mydatas',
  component: Layout,
  redirect: 'noRedirect',
  alwaysShow: true,
  name: 'MydataDemo',
  meta: {
    title: '气象数据',
    icon: 'tab'
  },
  children: [
    {
      path: 'mydataList',
      component: () => import('@/views/mydata/mydata-table'),
      name: 'mydataList',
      itemId: 5009,
      itemKey: '99933',
      meta: { title: '正点报', itemKey: '5009' }
    }, {
      path: 'mydataList',
      component: () => import('@/views/mydata/mydata-table'),
      name: 'mydataList',
      itemId: 5009,
      itemKey: '99933',
      meta: { title: '隐患通告', itemKey: '5009' }
    }, {
      // path: 'create/:id(\\d+)',
      path: 'createOrEdit/:itemId(\\d+)',
      component: () => import('@/views/mydata/mydata-createOrEdit'),
      name: 'CreateSample',
      meta: { title: 'Create Data' },
      hidden: true
    }
  ]
}

export default mydatasRouter
