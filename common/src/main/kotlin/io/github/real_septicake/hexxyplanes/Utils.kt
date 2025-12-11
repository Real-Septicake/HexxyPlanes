package io.github.real_septicake.hexxyplanes

import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.NullIota
import at.petrak.hexcasting.api.casting.iota.Vec3Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapInvalidIota
import at.petrak.hexcasting.api.casting.mishaps.MishapNotEnoughArgs
import com.mojang.datafixers.util.Either
import io.github.real_septicake.hexxyplanes.casting.iota.PlaneIota
import net.minecraft.core.Vec3i
import net.minecraft.world.phys.Vec3

fun List<Iota>.getPlane(idx: Int, argc: Int = 0): PlaneIota {
    val x = this.getOrElse(idx) { throw MishapNotEnoughArgs(idx + 1, this.size) }
    if(x !is PlaneIota)
        throw MishapInvalidIota.ofType(x, if(argc == 0) idx else argc - (idx + 1), "plane")
    return x
}

fun Vec3.toVecI(): Vec3i {
    return Vec3i(this.x.toInt(), this.y.toInt(), this.z.toInt())
}

fun List<Iota>.getVecOrNull(idx: Int, argc: Int = 0) : Either<Vec3, NullIota> {
    val x = this.getOrElse(idx) { throw MishapNotEnoughArgs(idx + 1, this.size) }
    return when(x) {
        is Vec3Iota -> Either.left(x.vec3)
        is NullIota -> Either.right(x)
        else -> throw MishapInvalidIota.of(
            x,
            if (argc == 0) idx else argc - (idx + 1),
            "numvec"
        )
    }
}