//package per.study.dp.context;
//
//public class ActionContext {
//    private static final ThreadLocal<Context> context = ThreadLocal.withInitial(Context::new);
//
//    public static Context get() {
//        return context.get();
//    }
//
//    // 每一个线程都会有一个独立的Context实例
//    static class Context {
//        // Context中的其他成员
//        private Configuration configuration;
//        private OtherResource otherResource;
//
//        public Configuration getConfiguration() {
//            return configuration;
//        }
//
//        public void setConfiguration(Configuration configuration) {
//            this.configuration = configuration;
//        }
//
//        public OtherResource getOtherResource() {
//            return otherResource;
//        }
//
//        public void setOtherResource(OtherResource otherResource) {
//            this.otherResource = otherResource;
//        }
//    }
//}
