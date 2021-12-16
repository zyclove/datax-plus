/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const componentsRouter = {
  path: '/components',
  component: Layout,
  redirect: 'noRedirect',
  name: 'ComponentDemo',
  meta: {
    title: '我的工作',
    icon: 'component'
  },
  children: [
    { path: 'markdown',
      component: () => import('@/views/data-source/data-source-table'),
      name: 'data-source',
      meta: { title: '数据源' }
    }, {
      // path: 'create/:id(\\d+)',
      path: 'data-source-create-or-edit/:dataSourceId(\\d+)',
      component: () => import('@/views/data-source/data-source-create-or-edit'),
      name: 'CreateSample',
      meta: { title: 'Create data source' },
      hidden: true
    }, {
      path: 'works-record-table',
      component: () => import('@/views/myworks/works-record-table'),
      name: 'works-record-table',
      meta: { title: '工作记录' }
    }, {
      // path: 'create/:id(\\d+)',
      path: 'works-record-createOrEdit/:dataJobId(\\d+)',
      component: () => import('@/views/myworks/works-record-createOrEdit'),
      name: 'CreateSample',
      meta: { title: 'Create Data' },
      hidden: true
    }, {
      path: 'markdown',
      component: () => import('@/views/mydata/mydata-table'),
      name: 'MarkdownDemo',
      meta: { title: '排班安排' }
    }
    // {
    //   path: 'json-editor',
    //   component: () => import('@/views/mydata/mydata-table'),
    //   name: 'JsonEditorDemo',
    //   meta: { title: '设备维护' }
    // },
    // {
    //   path: 'split-pane',
    //   component: () => import('@/views/mydata/mydata-table'),
    //   name: 'SplitpaneDemo',
    //   meta: { title: 'FOD管理' }
    // },
    // {
    //   path: 'avatar-upload',
    //   component: () => import('@/views/mydata/mydata-table'),
    //   name: 'AvatarUploadDemo',
    //   meta: { title: '步行巡视' }
    // },
    // {
    //   path: 'dropzone',
    //   component: () => import('@/views/mydata/mydata-table'),
    //   name: 'DropzoneDemo',
    //   meta: { title: '不停航施工' }
    // }
  ]
}

export default componentsRouter
