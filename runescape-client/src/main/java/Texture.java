import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("ds")
@Implements("Texture")
public class Texture extends Node {
   @ObfuscatedName("u")
   static int[] field1784;
   @ObfuscatedName("l")
   int field1777;
   @ObfuscatedName("b")
   boolean field1778;
   @ObfuscatedName("e")
   @Export("fileIds")
   int[] fileIds;
   @ObfuscatedName("x")
   int[] field1780;
   @ObfuscatedName("p")
   int[] field1781;
   @ObfuscatedName("g")
   int[] field1786;
   @ObfuscatedName("n")
   int field1783;
   @ObfuscatedName("o")
   int field1782;
   @ObfuscatedName("c")
   @Export("pixels")
   int[] pixels;
   @ObfuscatedName("v")
   @Export("loaded")
   boolean loaded;

   @ObfuscatedSignature(
      signature = "(Lgb;)V"
   )
   Texture(Buffer var1) {
      this.loaded = false;
      this.field1777 = var1.readUnsignedShort();
      this.field1778 = var1.readUnsignedByte() == 1;
      int var2 = var1.readUnsignedByte();
      if(var2 >= 1 && var2 <= 4) {
         this.fileIds = new int[var2];

         int var3;
         for(var3 = 0; var3 < var2; ++var3) {
            this.fileIds[var3] = var1.readUnsignedShort();
         }

         if(var2 > 1) {
            this.field1780 = new int[var2 - 1];

            for(var3 = 0; var3 < var2 - 1; ++var3) {
               this.field1780[var3] = var1.readUnsignedByte();
            }
         }

         if(var2 > 1) {
            this.field1781 = new int[var2 - 1];

            for(var3 = 0; var3 < var2 - 1; ++var3) {
               this.field1781[var3] = var1.readUnsignedByte();
            }
         }

         this.field1786 = new int[var2];

         for(var3 = 0; var3 < var2; ++var3) {
            this.field1786[var3] = var1.readInt();
         }

         this.field1783 = var1.readUnsignedByte();
         this.field1782 = var1.readUnsignedByte();
         this.pixels = null;
      } else {
         throw new RuntimeException();
      }
   }

   @ObfuscatedName("t")
   @ObfuscatedSignature(
      signature = "(DILjc;)Z"
   )
   boolean method2680(double var1, int var3, IndexDataBase var4) {
      int var5;
      for(var5 = 0; var5 < this.fileIds.length; ++var5) {
         if(var4.getRecordFlat(this.fileIds[var5]) == null) {
            return false;
         }
      }

      var5 = var3 * var3;
      this.pixels = new int[var5];

      for(int var6 = 0; var6 < this.fileIds.length; ++var6) {
         IndexedSprite var7 = class279.method4934(var4, this.fileIds[var6]);
         var7.normalize();
         byte[] var8 = var7.pixels;
         int[] var9 = var7.palette;
         int var10 = this.field1786[var6];
         if((var10 & -16777216) == 16777216) {
            ;
         }

         if((var10 & -16777216) == 33554432) {
            ;
         }

         int var11;
         int var12;
         int var13;
         int var14;
         if((var10 & -16777216) == 50331648) {
            var11 = var10 & 16711935;
            var12 = var10 >> 8 & 255;

            for(var13 = 0; var13 < var9.length; ++var13) {
               var14 = var9[var13];
               if(var14 >> 8 == (var14 & 65535)) {
                  var14 &= 255;
                  var9[var13] = var11 * var14 >> 8 & 16711935 | var12 * var14 & 65280;
               }
            }
         }

         for(var11 = 0; var11 < var9.length; ++var11) {
            var9[var11] = Graphics3D.adjustRGB(var9[var11], var1);
         }

         if(var6 == 0) {
            var11 = 0;
         } else {
            var11 = this.field1780[var6 - 1];
         }

         if(var11 == 0) {
            if(var3 == var7.width) {
               for(var12 = 0; var12 < var5; ++var12) {
                  this.pixels[var12] = var9[var8[var12] & 255];
               }
            } else if(var7.width == 64 && var3 == 128) {
               var12 = 0;

               for(var13 = 0; var13 < var3; ++var13) {
                  for(var14 = 0; var14 < var3; ++var14) {
                     this.pixels[var12++] = var9[var8[(var13 >> 1 << 6) + (var14 >> 1)] & 255];
                  }
               }
            } else {
               if(var7.width != 128 || var3 != 64) {
                  throw new RuntimeException();
               }

               var12 = 0;

               for(var13 = 0; var13 < var3; ++var13) {
                  for(var14 = 0; var14 < var3; ++var14) {
                     this.pixels[var12++] = var9[var8[(var14 << 1) + (var13 << 1 << 7)] & 255];
                  }
               }
            }
         }

         if(var11 == 1) {
            ;
         }

         if(var11 == 2) {
            ;
         }

         if(var11 == 3) {
            ;
         }
      }

      return true;
   }

   @ObfuscatedName("q")
   @Export("resetPixels")
   void resetPixels() {
      this.pixels = null;
   }

   @ObfuscatedName("i")
   void method2674(int var1) {
      if(this.pixels != null) {
         short var2;
         int var3;
         int var4;
         int var5;
         int var6;
         int var7;
         int[] var10;
         if(this.field1783 == 1 || this.field1783 == 3) {
            if(field1784 == null || field1784.length < this.pixels.length) {
               field1784 = new int[this.pixels.length];
            }

            if(this.pixels.length == 4096) {
               var2 = 64;
            } else {
               var2 = 128;
            }

            var3 = this.pixels.length;
            var4 = var2 * this.field1782 * var1;
            var5 = var3 - 1;
            if(this.field1783 == 1) {
               var4 = -var4;
            }

            for(var6 = 0; var6 < var3; ++var6) {
               var7 = var6 + var4 & var5;
               field1784[var6] = this.pixels[var7];
            }

            var10 = this.pixels;
            this.pixels = field1784;
            field1784 = var10;
         }

         if(this.field1783 == 2 || this.field1783 == 4) {
            if(field1784 == null || field1784.length < this.pixels.length) {
               field1784 = new int[this.pixels.length];
            }

            if(this.pixels.length == 4096) {
               var2 = 64;
            } else {
               var2 = 128;
            }

            var3 = this.pixels.length;
            var4 = this.field1782 * var1;
            var5 = var2 - 1;
            if(this.field1783 == 2) {
               var4 = -var4;
            }

            for(var6 = 0; var6 < var3; var6 += var2) {
               for(var7 = 0; var7 < var2; ++var7) {
                  int var8 = var6 + var7;
                  int var9 = var6 + (var7 + var4 & var5);
                  field1784[var8] = this.pixels[var9];
               }
            }

            var10 = this.pixels;
            this.pixels = field1784;
            field1784 = var10;
         }

      }
   }
}
