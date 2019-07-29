package cachefsmain;

import java.lang.System;

@io.objectbox.annotation.Entity()
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\nJ\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u0010\"\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016J\u0010\u0010#\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016JT\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010%J\u0013\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010)\u001a\u00020*H\u00d6\u0001J\t\u0010+\u001a\u00020\u0005H\u00d6\u0001R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\f\"\u0004\b\u001b\u0010\u000eR\u001e\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u001c\u0010\u0016\"\u0004\b\u001d\u0010\u0018\u00a8\u0006,"}, d2 = {"Lcachefsmain/Record;", "", "ID", "", "ORIGINAL_ID", "", "CALLING_NUM", "CALLED_NUM", "START_TIME_I", "INSERT_TIME", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;)V", "getCALLED_NUM", "()Ljava/lang/String;", "setCALLED_NUM", "(Ljava/lang/String;)V", "getCALLING_NUM", "setCALLING_NUM", "getID", "()J", "setID", "(J)V", "getINSERT_TIME", "()Ljava/lang/Long;", "setINSERT_TIME", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getORIGINAL_ID", "setORIGINAL_ID", "getSTART_TIME_I", "setSTART_TIME_I", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;)Lcachefsmain/Record;", "equals", "", "other", "hashCode", "", "toString", "cachefs"})
public final class Record {
    @io.objectbox.annotation.Id()
    private long ID;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String ORIGINAL_ID;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String CALLING_NUM;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String CALLED_NUM;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long START_TIME_I;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long INSERT_TIME;
    
    public final long getID() {
        return 0L;
    }
    
    public final void setID(long p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getORIGINAL_ID() {
        return null;
    }
    
    public final void setORIGINAL_ID(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCALLING_NUM() {
        return null;
    }
    
    public final void setCALLING_NUM(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCALLED_NUM() {
        return null;
    }
    
    public final void setCALLED_NUM(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getSTART_TIME_I() {
        return null;
    }
    
    public final void setSTART_TIME_I(@org.jetbrains.annotations.Nullable()
    java.lang.Long p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getINSERT_TIME() {
        return null;
    }
    
    public final void setINSERT_TIME(@org.jetbrains.annotations.Nullable()
    java.lang.Long p0) {
    }
    
    public Record(long ID, @org.jetbrains.annotations.Nullable()
    java.lang.String ORIGINAL_ID, @org.jetbrains.annotations.Nullable()
    java.lang.String CALLING_NUM, @org.jetbrains.annotations.Nullable()
    java.lang.String CALLED_NUM, @org.jetbrains.annotations.Nullable()
    java.lang.Long START_TIME_I, @org.jetbrains.annotations.Nullable()
    java.lang.Long INSERT_TIME) {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final cachefsmain.Record copy(long ID, @org.jetbrains.annotations.Nullable()
    java.lang.String ORIGINAL_ID, @org.jetbrains.annotations.Nullable()
    java.lang.String CALLING_NUM, @org.jetbrains.annotations.Nullable()
    java.lang.String CALLED_NUM, @org.jetbrains.annotations.Nullable()
    java.lang.Long START_TIME_I, @org.jetbrains.annotations.Nullable()
    java.lang.Long INSERT_TIME) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
}